package com.example.music.model.api

import com.google.gson.annotations.SerializedName

data class ApiAlbumResponse(@SerializedName("data") val data: List<ApiAlbum>)

data class ApiAlbum(@SerializedName("id") val id: Long,
                    @SerializedName("title") val title: String,
                    @SerializedName("cover_small") val coverSmall: String,
                    @SerializedName("cover_big") val coverBig: String)