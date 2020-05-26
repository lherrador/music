package com.example.music

import android.app.Application
import com.example.music.configuration.musicApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


open class MusicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MusicApplication)
            modules(musicApplicationModule)
        }
    }
}
