package com.example.music.help

import com.example.music.model.local.Album

object SharedMockData {
    val album =
        Album(id = 1, title = "title", coverSmall = "coverSmall", coverBig = "coverBig")
    val album2 =
        Album(id = 2, title = "title2", coverSmall = "coverSmall2", coverBig = "coverBig2")
}