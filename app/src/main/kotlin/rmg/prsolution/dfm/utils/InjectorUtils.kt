package rmg.prsolution.dfm.utils

import android.content.ComponentName
import android.content.Context
import rmg.prsolution.dfm.ui.viewmodels.MediaItemFragmentViewModel
import rmg.prsolution.dfm.ui.MediaSessionConnection
import rmg.prsolution.dfm.media.MusicService
import rmg.prsolution.dfm.ui.viewmodels.MainActivityViewModel

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {
    private fun provideMediaSessionConnection(context: Context): MediaSessionConnection {
        return MediaSessionConnection.getInstance(context,
                ComponentName(context, MusicService::class.java))
    }

    fun provideMainActivityViewModel(context: Context): MainActivityViewModel.Factory {
        val applicationContext = context.applicationContext
        val mediaSessionConnection = provideMediaSessionConnection(applicationContext)
        return MainActivityViewModel.Factory(mediaSessionConnection)
    }

    fun provideMediaItemFragmentViewModel(context: Context, mediaId: String)
            : MediaItemFragmentViewModel.Factory {
        val applicationContext = context.applicationContext
        val mediaSessionConnection = provideMediaSessionConnection(applicationContext)
        return MediaItemFragmentViewModel.Factory(mediaId, mediaSessionConnection)
    }
}