package com.example.music.model.local

data class Album(val id: Long, val title: String, val coverSmall: String, val coverBig: String)
data class Track(val id: Long, val title: String, val preview: String)

data class AlbumWithTracks(val album: Album, val tracks: List<Track>)