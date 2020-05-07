package com.example.music.help

import com.example.music.model.local.Album

object SharedMockData {
    val album =
        Album(id = 1, url = "url", title = "title", thumbnailUrl = "thumbnailUrl", albumId = 1)
    val album2 =
        Album(id = 2, url = "url2", title = "title2", thumbnailUrl = "thumbnailUrl2", albumId = 2)
}