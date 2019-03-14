package com.example.android.uamp.utils

import android.content.ComponentName
import android.content.Context
import com.example.android.uamp.viewmodels.MediaItemFragmentViewModel
import com.example.android.uamp.MediaSessionConnection
import com.example.android.uamp.media.MusicService
import com.example.android.uamp.viewmodels.MainActivityViewModel

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