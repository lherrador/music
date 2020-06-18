package com.example.music.api

import com.example.music.BuildConfig
import com.example.music.model.api.ApiAlbum
import com.example.music.model.api.ApiAlbumResponse
import com.example.music.model.api.ApiTrack
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MusicApiClient(baseUrl: String) : IMusicApiClient {
    private val musicApi: MusicApi

    companion object {
        const val USER_ID = 2529L
    }

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder
                .addInterceptor(logging)
                .addNetworkInterceptor(StethoInterceptor())
        }
        val client = okHttpClientBuilder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        musicApi = retrofit.create(MusicApi::class.java)
    }

    override fun findAlbum(): Single<List<ApiAlbum>> {
        return musicApi.findAlbum(USER_ID).map {
            it.data
        }
    }

    override fun findTrack(albumId: Long): Single<List<ApiTrack>> {
        return musicApi.findTrack(albumId).map {
            it.data
        }
    }
}
