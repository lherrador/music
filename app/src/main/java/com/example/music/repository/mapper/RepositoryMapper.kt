package com.example.music.repository.mapper

import com.example.music.model.api.ApiAlbum
import com.example.music.model.local.Album
import com.example.music.model.persist.PersistAlbum

class RepositoryMapper {
    fun mapRemoteAlbumToLocal(apiAlbumList: List<ApiAlbum>): List<Album> {
        return apiAlbumList.map {
            with(it) {
                Album(
                    id = id, albumId = albumId, thumbnailUrl = thumbnailUrl, title = title,
                    url = url
                )
            }
        }
    }

    fun mapPersistAlbumToLocal(persistAlbumList: List<PersistAlbum>): List<Album> {
        return persistAlbumList.map {
            with(it) {
                Album(
                    id = id, albumId = albumId, thumbnailUrl = thumbnailUrl, title = title,
                    url = url
                )
            }
        }
    }

    fun mapLocalAlbumToPersit(albumList: List<Album>): List<PersistAlbum> {
        return albumList.map {
            with(it) {
                PersistAlbum(
                    id = id, albumId = albumId, thumbnailUrl = thumbnailUrl, title = title,
                    url = url
                )
            }
        }
    }
}