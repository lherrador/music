package com.example.music.ui.uidata

data class AlbumContentUiData(val albumUiDataList: List<AlbumUiData>)
data class AlbumUiData(val id: Long, val description: String, val image: String)