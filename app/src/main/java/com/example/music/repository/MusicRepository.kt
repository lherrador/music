package com.example.music.repository

import com.example.music.model.local.Album

class MusicRepository(
    private val musicRemoteRepository: IMusicRemoteRepository,
    private val musicPersistRepository: IMusicPersistRepository
) :
    IMusicRepository {
    override suspend fun findAlbum(): List<Album> {
        var albumList = musicPersistRepository.findAlbum()

        if(albumList.isEmpty()) {
            albumList = musicRemoteRepository.findAlbum()
        }

        return albumList
    }
}

interface IMusicRepository {
    suspend fun findAlbum(): List<Album>
}