package com.example.music.repository

import com.example.music.model.local.Album
import com.example.music.model.local.Track
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
    override fun findAlbum(id : Long): Single<Album> {
        return musicPersistRepository.findAlbum(id)
    }

    override fun findTrack(albumId : Long): Single<List<Track>> {
        return musicRemoteRepository.findTrack(albumId)
    }
}

interface IMusicRepository {
    fun findAlbum(): Single<List<Album>>
    fun findAlbum(albumId : Long): Single<Album>
    fun findTrack(albumId : Long): Single<List<Track>>
}