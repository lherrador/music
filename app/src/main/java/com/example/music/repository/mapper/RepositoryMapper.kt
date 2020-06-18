package com.example.music.repository.mapper

import com.example.music.model.api.ApiAlbum
import com.example.music.model.api.ApiTrack
import com.example.music.model.local.Album
import com.example.music.model.local.Track
import com.example.music.model.persist.PersistAlbum

class RepositoryMapper {
    fun mapRemoteAlbumToLocal(apiAlbumList: List<ApiAlbum>): List<Album> {
        return apiAlbumList.map {
            with(it) {
                Album(
                    id = id, title = title,
                    coverBig = coverBig, coverSmall = coverSmall
                )
            }
        }
    }

    fun mapRemoteTrackToLocal(apiTrackList: List<ApiTrack>): List<Track> {
        return apiTrackList.map {
            with(it) {
                Track(
                    id = id, title = title, preview = preview
                )
            }
        }
    }

    fun mapPersistAlbumToLocal(persistAlbumList: List<PersistAlbum>): List<Album> {
        return persistAlbumList.map {
            mapPersistAlbumToLocal(it)
        }
    }

    fun mapPersistAlbumToLocal(persistAlbum: PersistAlbum): Album {
        with(persistAlbum) {
            return Album(
                id = id, title = title,
                coverBig = coverBig, coverSmall = coverSmall
            )
        }
    }

    fun mapLocalAlbumToPersit(albumList: List<Album>): List<PersistAlbum> {
        return albumList.map {
            with(it) {
                PersistAlbum(
                    id = id, title = title,
                    coverBig = coverBig, coverSmall = coverSmall
                )
            }
        }
    }
}