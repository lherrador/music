package com.example.music.repository

import com.example.music.dao.IAlbumDao
import com.example.music.model.local.Album
import com.example.music.repository.mapper.RepositoryMapper
import io.reactivex.Completable
import io.reactivex.Single

class MusicPersistRespository(val albumDao: IAlbumDao) : IMusicPersistRespository {

    private val repositoryMapper = RepositoryMapper()

    override fun findAlbum(): Single<List<Album>> {
        return Single.fromCallable {
            repositoryMapper.mapPersistAlbumToLocal(albumDao.findAlbum())
        }
    }

    override fun createAlbum(albumList: List<Album>): Completable {
        return Completable.fromAction {
            albumDao.create(repositoryMapper.mapLocalAlbumToPersit(albumList))
        }
    }
}


interface IMusicPersistRespository {
    fun findAlbum(): Single<List<Album>>
    fun createAlbum(albumList: List<Album>): Completable
}