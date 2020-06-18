package com.example.music.ui.uidata

data class AlbumContentUiData(val albumUiDataList: List<AlbumUiData>)
data class AlbumUiData(val id: Long, val description: String, val image: String)

data class AlbumDetailUiData(val id: Long, val description: String, val image: String, val trackList: List<TrackUiData>)
data class TrackUiData(val id: Long, val description: String, val image: String, val preview : String)