package com.example.music.repository

import com.example.music.model.local.Album
import io.reactivex.Single

class MusicRepository(
    private val musicRemoteRepository: IMusicRemoteRepository,
    private val musicPersistRepository: IMusicPersistRepository
) :
    IMusicRepository {
    override fun findAlbum(): Single<List<Album>> {
        return musicPersistRepository.findAlbum().flatMap {
            if (it.isEmpty()) {
                musicRemoteRepository.findAlbum().flatMap { albumList ->
                    musicPersistRepository.createAlbum(albumList).toSingleDefault(albumList)
                }
            } else {
                Single.just(it)
            }
        }
    }
}

interface IMusicRepository {
    fun findAlbum(): Single<List<Album>>
}