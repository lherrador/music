package com.example.music.api

import com.example.music.model.api.ApiAlbumResponse
import com.example.music.model.api.ApiTrackResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApi {
    @GET("user/{userId}/albums")
    fun findAlbum(@Path("userId") userId: Long): Single<ApiAlbumResponse>

    @GET("album/{albumId}/tracks")
    fun findTrack(@Path("albumId") albumId: Long): Single<ApiTrackResponse>
}
