package rmg.prsolution.dfm.ui

import android.media.AudioManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.longToast
import org.koin.androidx.viewmodel.ext.viewModel
import rmg.prsolution.dfm.AppConfiguration.getRootViewContainerFor
import rmg.prsolution.dfm.AppConfiguration.riseAndShine
import rmg.prsolution.dfm.R
import rmg.prsolution.dfm.data.repository.resouces.ResourceState
import rmg.prsolution.dfm.di.loadAppModules
import rmg.prsolution.dfm.ui.viewmodels.ChannelViewModel
import rmg.prsolution.dfm.ui.viewmodels.MainActivityViewModel
import rmg.prsolution.dfm.utils.Event
import rmg.prsolution.dfm.utils.InjectorUtils

class MainActivity : AppCompatActivity() {
    private lateinit var mMainVm: MainActivityViewModel
    private val mChanelVm: ChannelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Koin  DI init
        loadAppModules()

        val container: ViewGroup = getRootViewContainerFor(this)
        val home: View = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)

        // Wake up activity in devices on run
        riseAndShine(this)

        mChanelVm.channels.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> {}
                    ResourceState.ERROR -> {
                    }
                    ResourceState.SUCCESS -> {
                        longToast("Channel list size: ${it.data?.items?.size}")
                    }
                }
            }
        })


        // Since UAMP is a music player, the volume controls should adjust the music volume while
        // in the app.
        volumeControlStream = AudioManager.STREAM_MUSIC

        mMainVm = ViewModelProviders
                .of(this, InjectorUtils.provideMainActivityViewModel(this))
                .get(MainActivityViewModel::class.java)

        /**
         * Observe changes to the [MainActivityViewModel.rootMediaId]. When the app starts,
         * and the UI connects to [MusicService], this will be updated and the app will show
         * the initial list of media items.
         */
        mMainVm.rootMediaId.observe(this@MainActivity, Observer<String> { rootMediaId ->
                    if (rootMediaId != null) {
                        navigateToMediaItem(rootMediaId)
                    }
                })


        /**
         * Observe [MainActivityViewModel.navigateToMediaItem] for [Event]s indicating
         * the user has requested to browse to a different [MediaItemData].
         */
        mMainVm.navigateToMediaItem.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { mediaId ->
                navigateToMediaItem(mediaId)
            }
        })
    }

    private fun navigateToMediaItem(mediaId: String) {
        var fragment: MediaItemFragment? = getBrowseFragment(mediaId)

        if (fragment == null) {
            fragment = MediaItemFragment.newInstance(mediaId)

            supportFragmentManager.beginTransaction()
                    .apply {
                        replace(R.id.browseFragment, fragment, mediaId)

                        // If this is not the top level media (root), we add it to the fragment
                        // back stack, so that actionbar toggle and Back will work appropriately:
                        if (!isRootId(mediaId)) {
                            addToBackStack(null)
                        }
                    }
                    .commit()
        }
    }

    private fun isRootId(mediaId: String) = mediaId == mMainVm.rootMediaId.value

    private fun getBrowseFragment(mediaId: String): MediaItemFragment? {
        return supportFragmentManager.findFragmentByTag(mediaId) as MediaItemFragment?
    }

    override fun onStart() {
        super.onStart()
        mChanelVm.getChannels()
    }
}
