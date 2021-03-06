package rmg.prsolution.dfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rmg.prsolution.dfm.utils.InjectorUtils
import rmg.prsolution.dfm.ui.viewmodels.MainActivityViewModel
import rmg.prsolution.dfm.ui.viewmodels.MediaItemFragmentViewModel
import kotlinx.android.synthetic.main.fragment_mediaitem_list.list
import kotlinx.android.synthetic.main.fragment_mediaitem_list.loadingSpinner
import kotlinx.android.synthetic.main.fragment_mediaitem_list.networkError
import rmg.prsolution.dfm.R

/**
 * A fragment representing a list of MediaItems.
 */
class MediaItemFragment : androidx.fragment.app.Fragment() {
    private lateinit var mediaId: String
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var mediaItemFragmentViewModel: MediaItemFragmentViewModel

    private val listAdapter = MediaItemAdapter { clickedItem ->
        mainActivityViewModel.mediaItemClicked(clickedItem)
    }

    companion object {
        fun newInstance(mediaId: String): MediaItemFragment {

            return MediaItemFragment().apply {
                arguments = Bundle().apply {
                    putString(MEDIA_ID_ARG, mediaId)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mediaitem_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Always true, but lets lint know that as well.
        val context = activity ?: return
        mediaId = arguments?.getString(MEDIA_ID_ARG) ?: return

        mainActivityViewModel = ViewModelProviders
                .of(context, InjectorUtils.provideMainActivityViewModel(context))
                .get(MainActivityViewModel::class.java)

        mediaItemFragmentViewModel = ViewModelProviders
                .of(this, InjectorUtils.provideMediaItemFragmentViewModel(context, mediaId))
                .get(MediaItemFragmentViewModel::class.java)
        mediaItemFragmentViewModel.mediaItems.observe(this,
                Observer<List<MediaItemData>> { list ->
                    val isEmptyList = list?.isEmpty() ?: true
                    loadingSpinner.visibility = View.GONE
                    networkError.visibility = if (isEmptyList) View.VISIBLE else View.GONE
                    listAdapter.submitList(list)
                })

        // Set the adapter
        if (list is RecyclerView) {
            list.layoutManager = LinearLayoutManager(list.context)
            list.adapter = listAdapter
        }
    }
}

private const val MEDIA_ID_ARG = "rmg.prsolution.dfm.ui.MediaItemFragment.MEDIA_ID"
