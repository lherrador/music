package com.example.music.api

import androidx.annotation.WorkerThread
import com.example.music.model.api.ApiAlbum
import io.reactivex.Single

interface IMusicApiClient {

	@WorkerThread
	fun findAlbum(): Single<List<ApiAlbum>>
}
