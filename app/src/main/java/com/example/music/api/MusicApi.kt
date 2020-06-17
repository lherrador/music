package com.example.music.api

import com.example.music.model.api.ApiAlbumResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MusicApi {
	@GET("albums")
	fun findAlbum(): Single<ApiAlbumResponse>
}
