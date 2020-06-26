package com.example.music.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.example.music.model.persist.PersistAlbum
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AlbumDaoTest {
    private lateinit var albumDao: IAlbumDao
    private lateinit var musicRoomDatabase: MusicRoomDatabase

    private val albumPersist =
        PersistAlbum(
            id = 1,
            title = "title",
            coverBig = "coverBig",
            coverSmall = "coverSmall"
        )
    private val albumPersist2 =
        PersistAlbum(
            id = 2,
            title = "title2",
            coverBig = "coverBig2",
            coverSmall = "coverSmall2"
        )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        musicRoomDatabase = Room.inMemoryDatabaseBuilder(
            context, MusicRoomDatabase::class.java
        ).build()
        albumDao = musicRoomDatabase.albumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        musicRoomDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAlbumAndReadFromList() {
        albumDao.create(listOf(albumPersist, albumPersist2))

        val persistAlbumList = albumDao.findAlbum()

        assert(persistAlbumList.size == 2)
    }
}