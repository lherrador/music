package com.example.music.api

import androidx.annotation.WorkerThread
import com.example.music.model.api.ApiAlbum

interface IMusicApiClient {

	@WorkerThread
	suspend fun findAlbum(): List<ApiAlbum>
}
