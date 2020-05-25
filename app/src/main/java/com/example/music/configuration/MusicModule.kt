package com.example.music.configuration

import android.content.Context
import com.example.music.api.IMusicApiClient
import com.example.music.api.MusicApiClient
import com.example.music.dao.IAlbumDao
import com.example.music.dao.MusicRoomDatabase
import com.example.music.domain.IMusicDomain
import com.example.music.domain.MusicDomain
import com.example.music.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MusicModule constructor(private val context: Context, private val baseUrl: String) {

    @Singleton
    @Provides
    fun provideMusicApiClient(): IMusicApiClient {
        return MusicApiClient(baseUrl)
    }

    @Singleton
    @Provides
    fun provideMusicRemoteRepository(musicApiClient: IMusicApiClient): IMusicRemoteRepository {
        return MusicRemoteRepository(musicApiClient)
    }

    @Singleton
    @Provides
    fun provideMusicPersistRepository(albumDao: IAlbumDao): IMusicPersistRepository {
        return MusicPersistRepository(albumDao)
    }

    @Singleton
    @Provides
    fun provideMusicRoomDatabase(): MusicRoomDatabase {
        return MusicRoomDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAlbumDao(musicRoomDatabase: MusicRoomDatabase): IAlbumDao {
        return musicRoomDatabase.albumDao()
    }

    @Singleton
    @Provides
    fun provideMusicRepository(
        musicRemoteRepository: IMusicRemoteRepository,
        musicPersistRepository: IMusicPersistRepository
    ): IMusicRepository {
        return MusicRepository(musicRemoteRepository, musicPersistRepository)
    }

    @Singleton
    @Provides
    fun provideMusicDomain(musicRepository: IMusicRepository): IMusicDomain {
        return MusicDomain(musicRepository)
    }
}