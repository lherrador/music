package com.example.music

import android.app.Application
import com.example.music.api.MusicApiClient
import com.example.music.dao.MusicRoomDatabase
import com.example.music.domain.IMusicDomain
import com.example.music.domain.MusicDomain
import com.example.music.repository.MusicPersistRepository
import com.example.music.repository.MusicRemoteRepository
import com.example.music.repository.MusicRepository


open class MusicApplication : Application() {

    //TODO use Dagger for the injection
    private val musicDomain: IMusicDomain by lazy {
        MusicDomain(
            MusicRepository(
                MusicRemoteRepository(MusicApiClient(this.getString(R.string.base_url))),
                MusicPersistRepository(MusicRoomDatabase.getDatabase(this).albumDao())
            )
        )
    }

    fun findMusicDomain() = musicDomain
}
