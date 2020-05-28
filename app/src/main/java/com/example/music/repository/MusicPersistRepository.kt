package com.example.music.repository

import com.example.music.dao.IAlbumDao
import com.example.music.model.local.Album
import com.example.music.repository.mapper.RepositoryMapper

class MusicPersistRepository(private val albumDao: IAlbumDao) : IMusicPersistRepository {

    private val repositoryMapper = RepositoryMapper()

    override suspend fun findAlbum(): List<Album> {
        return repositoryMapper.mapPersistAlbumToLocal(albumDao.findAlbum())
    }

    override suspend fun createAlbum(albumList: List<Album>) {
        albumDao.create(repositoryMapper.mapLocalAlbumToPersit(albumList))
    }
}


interface IMusicPersistRepository {
    suspend fun findAlbum(): List<Album>
    suspend fun createAlbum(albumList: List<Album>)
}