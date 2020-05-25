package com.example.music

import android.app.Application
import com.example.music.api.MusicApiClient
import com.example.music.configuration.DaggerIMusicComponent
import com.example.music.configuration.IMusicComponent
import com.example.music.configuration.MusicModule
import com.example.music.dao.MusicRoomDatabase
import com.example.music.domain.IMusicDomain
import com.example.music.domain.MusicDomain
import com.example.music.repository.MusicPersistRepository
import com.example.music.repository.MusicRemoteRepository
import com.example.music.repository.MusicRepository


open class MusicApplication : Application() {

    private val musicComponent: IMusicComponent by lazy {
        DaggerIMusicComponent.builder()
            .musicModule(MusicModule(this, this.getString(R.string.base_url))).build()
    }

    fun findMusicComponent() = musicComponent
}
