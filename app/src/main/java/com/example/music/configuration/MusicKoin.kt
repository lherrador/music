package com.example.music.configuration

import com.example.music.R
import com.example.music.api.MusicApiClient
import com.example.music.dao.MusicRoomDatabase
import com.example.music.domain.IMusicDomain
import com.example.music.domain.MusicDomain
import com.example.music.repository.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val musicApplicationModule = module {
    single { MusicRoomDatabase.getDatabase(androidApplication()).albumDao() }
    single<IMusicRemoteRepository> { MusicRemoteRepository(MusicApiClient(androidApplication().getString(R.string.base_url))) }
    single<IMusicPersistRespository> { MusicPersistRespository(get()) }
    single<IMusicRepository> { MusicRepository(get(), get()) }
    single<IMusicDomain> { MusicDomain(get()) }
}