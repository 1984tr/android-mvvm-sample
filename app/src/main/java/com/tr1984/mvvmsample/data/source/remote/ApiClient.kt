package com.tr1984.mvvmsample.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://mytest.api.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val api by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val client by lazy {
        OkHttpClient().newBuilder().apply {
            connectTimeout(12, TimeUnit.SECONDS)
            readTimeout(12, TimeUnit.SECONDS)
            writeTimeout(12, TimeUnit.SECONDS)
        }.build()
    }

}