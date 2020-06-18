package com.example.music.ui.mapper

import com.example.music.model.local.Album
import com.example.music.model.local.AlbumWithTracks
import com.example.music.ui.uidata.AlbumContentUiData
import com.example.music.ui.uidata.AlbumDetailUiData
import com.example.music.ui.uidata.AlbumUiData
import com.example.music.ui.uidata.TrackUiData

class UiDataMapper {

    fun mapAlbumListToUiData(albumList: List<Album>): AlbumContentUiData {
        return AlbumContentUiData(albumList.map {
            with(it) {
                AlbumUiData(id = id, description = title, image = coverSmall)
            }
        })
    }

    fun mapAlbumWithTracksToUiData(albumWithTracks: AlbumWithTracks): AlbumDetailUiData {
        val tracksListUiData = albumWithTracks.tracks.map {
            with(it) {
                TrackUiData(
                    id = id,
                    description = title,
                    image = albumWithTracks.album.coverSmall,
                    preview = preview
                )
            }
        }
        with(albumWithTracks.album) {
            return AlbumDetailUiData(
                id = id,
                description = title,
                image = coverBig,
                trackList = tracksListUiData
            )
        }
    }
}