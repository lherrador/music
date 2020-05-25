package com.example.music.configuration

import com.example.music.ui.AlbumActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MusicModule::class])
interface IMusicComponent {
    fun inject(app: AlbumActivity)
}