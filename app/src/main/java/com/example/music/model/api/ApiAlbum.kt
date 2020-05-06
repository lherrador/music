package com.example.music.model.api

import com.google.gson.annotations.SerializedName

data class ApiAlbum(@SerializedName("id") val id: Long,
                    @SerializedName("albumId") val albumId: Long,
                    @SerializedName("title") val title: String,
                    @SerializedName("url") val url: String,
                    @SerializedName("thumbnailUrl") val thumbnailUrl: String)