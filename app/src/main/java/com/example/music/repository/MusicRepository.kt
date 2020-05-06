package com.example.music.repository

import com.example.music.model.local.Album
import io.reactivex.Single

class MusicRepository(
    private val musicRemoteRepository: IMusicRemoteRepository,
    private val musicPersistRespository: MusicPersistRespository
) :
    IMusicRepository {
    override fun findAlbum(): Single<List<Album>> {
        return musicPersistRespository.findAlbum().flatMap {
            if (it.isEmpty()) {
                musicRemoteRepository.findAlbum().flatMap { albumList ->
                    musicPersistRespository.createAlbum(albumList).toSingleDefault(albumList)
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