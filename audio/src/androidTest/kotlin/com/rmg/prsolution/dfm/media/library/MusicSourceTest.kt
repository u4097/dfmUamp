package com.rmg.prsolution.dfm.media.library

import android.os.Bundle
import android.provider.MediaStore
import androidx.test.runner.AndroidJUnit4
import android.support.v4.media.MediaMetadataCompat
import rmg.prsolution.dfm.media.extensions.album
import rmg.prsolution.dfm.media.extensions.artist
import rmg.prsolution.dfm.media.extensions.genre
import rmg.prsolution.dfm.media.extensions.id
import rmg.prsolution.dfm.media.extensions.title
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import rmg.prsolution.dfm.media.library.AbstractMusicSource
import rmg.prsolution.dfm.media.library.STATE_ERROR
import rmg.prsolution.dfm.media.library.STATE_INITIALIZED

/**
 * A small set of Android integration tests for (primarily) [AbstractMusicSource].
 *
 * The tests all use an extension of [AbstractMusicSource] which is defined at the
 * bottom of this file: [TestMusicSource].
 */
@RunWith(AndroidJUnit4::class)
class MusicSourceTest {

    private val musicList = listOf<MediaMetadataCompat>(
            MediaMetadataCompat.Builder().apply {
                id = "ich_hasse_dich"
                title = "Ich hasse dich"
                album = "Speechless"
                artist = "Jemand"
                genre = "Folk"
            }.build(),

            MediaMetadataCompat.Builder().apply {
                id = "about_a_guy"
                title = "About a Guy"
                album = "Tales from the Render Farm"
                artist = "7 Developers and a Pastry Chef"
                genre = "Rock"
            }.build()
    )

    /** Variables for testing [MusicSource.whenReady] */
    var waiting = true

    @Test
    fun whenReady_waits() {
        val testSource = TestMusicSource(musicList)
        waiting = true

        testSource.whenReady { success ->
            Assert.assertEquals(success, true)
            waiting = false
        }
        Assert.assertEquals(waiting, true)
        testSource.prepare()
        Assert.assertEquals(waiting, false)
    }

    @Test
    fun whenReady_errorContinues() {
        val testSource = TestMusicSource(musicList)
        waiting = true

        testSource.whenReady { success ->
            Assert.assertEquals(success, false)
            waiting = false
        }
        Assert.assertEquals(waiting, true)
        testSource.error()
        Assert.assertEquals(waiting, false)
    }

    @Test
    fun searchByGenre_matches() {
        val testSource = TestMusicSource(musicList)
        testSource.prepare()

        val searchQuery = "Rock"
        val searchExtras = Bundle().apply {
            putString(MediaStore.EXTRA_MEDIA_FOCUS, MediaStore.Audio.Genres.ENTRY_CONTENT_TYPE)
            putString(MediaStore.EXTRA_MEDIA_GENRE, searchQuery)
        }
        val result = testSource.search(searchQuery, searchExtras)
        Assert.assertEquals(result.size, 1)
        Assert.assertEquals(result[0].id, "about_a_guy")
    }

    @Test
    fun searchByMedia_matches() {
        val testSource = TestMusicSource(musicList)
        testSource.prepare()

        val searchQuery = "About a Guy"
        val searchExtras = Bundle().apply {
            putString(MediaStore.EXTRA_MEDIA_FOCUS, MediaStore.Audio.Media.ENTRY_CONTENT_TYPE)
            putString(MediaStore.EXTRA_MEDIA_TITLE, searchQuery)
            putString(MediaStore.EXTRA_MEDIA_ALBUM, "Tales from the Render Farm")
            putString(MediaStore.EXTRA_MEDIA_ARTIST, "7 Developers and a Pastry Chef")
        }
        val result = testSource.search(searchQuery, searchExtras)
        Assert.assertEquals(result.size, 1)
        Assert.assertEquals(result[0].id, "about_a_guy")
    }

    @Test
    fun searchByMedia_nomatches() {
        val testSource = TestMusicSource(musicList)
        testSource.prepare()

        val searchQuery = "Kotlin in 31 Days"
        val searchExtras = Bundle().apply {
            putString(MediaStore.EXTRA_MEDIA_FOCUS, MediaStore.Audio.Media.ENTRY_CONTENT_TYPE)
            putString(MediaStore.EXTRA_MEDIA_TITLE, searchQuery)
            putString(MediaStore.EXTRA_MEDIA_ALBUM, "Delegated by Lazy")
            putString(MediaStore.EXTRA_MEDIA_ARTIST, "Brainiest Jet")
        }
        val result = testSource.search(searchQuery, searchExtras)
        Assert.assertEquals(result.size, 0)
    }

    @Test
    fun searchByKeyword_fallback() {
        val testSource = TestMusicSource(musicList)
        testSource.prepare()

        val searchQuery = "hasse"
        val searchExtras = Bundle.EMPTY
        val result = testSource.search(searchQuery, searchExtras)
        Assert.assertEquals(result.size, 1)
        Assert.assertEquals(result[0].id, "ich_hasse_dich")
    }
}

class TestMusicSource(private val music: List<MediaMetadataCompat>
) : AbstractMusicSource(), Iterable<MediaMetadataCompat> by music {
    fun prepare() {
        state = STATE_INITIALIZED
    }

    fun error() {
        state = STATE_ERROR
    }
}