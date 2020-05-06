package com.example.music

import android.app.Application
import com.facebook.stetho.Stetho


class DebugApplication : MusicApplication() {

    override fun onCreate() {
        super.onCreate()

        //TODO use only on debug
        Stetho.initializeWithDefaults(this)
    }
}
