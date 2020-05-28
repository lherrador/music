package com.example.music.api

import com.example.music.model.api.ApiAlbum
import com.example.music.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MusicApiClient(baseUrl: String) : IMusicApiClient {
    private val musicApi: MusicApi

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
            .client(client)
            .build()

        musicApi = retrofit.create(MusicApi::class.java)
    }

    override suspend fun findAlbum(): List<ApiAlbum> {
        return musicApi.findAlbum()
    }
}
