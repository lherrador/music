package com.example.music.api

import androidx.annotation.WorkerThread
import com.example.music.model.api.ApiAlbum
import com.example.music.model.api.ApiTrack
import io.reactivex.Single

interface IMusicApiClient {

    @WorkerThread
    fun findAlbum(): Single<List<ApiAlbum>>

    @WorkerThread
    fun findTrack(albumId: Long): Single<List<ApiTrack>>
}
