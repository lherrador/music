package com.example.music

import com.facebook.stetho.Stetho


class DebugApplication : MusicApplication() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}
