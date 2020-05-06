package com.example.music.api

import com.example.music.model.api.ApiAlbum
import io.reactivex.Single
import retrofit2.http.GET

interface MusicApi {
	@GET("technical-test.json")
	fun findAlbum(): Single<List<ApiAlbum>>
}
