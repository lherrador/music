package com.example.music.ui.mapper

import com.example.music.model.local.Album
import com.example.music.ui.uidata.AlbumContentUiData
import com.example.music.ui.uidata.AlbumUiData

class UiDataMapper {

    fun mapAlbumListToUiData(albumList: List<Album>): AlbumContentUiData {
        return AlbumContentUiData(albumList.map {
            with(it) {
                AlbumUiData(id = id, description = title, image = coverSmall)
            }
        })
    }

    fun mapAlbumToUiData(album: Album): AlbumUiData {
        with(album) {
            return AlbumUiData(id = id, description = title, image = coverBig)
        }
    }
}