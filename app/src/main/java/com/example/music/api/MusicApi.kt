package com.example.music.api

import com.example.music.model.api.ApiAlbum
import retrofit2.http.GET

interface MusicApi {
	@GET("technical-test.json")
	suspend fun findAlbum(): List<ApiAlbum>
}
