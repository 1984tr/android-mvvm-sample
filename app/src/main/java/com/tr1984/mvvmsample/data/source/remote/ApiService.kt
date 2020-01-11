package com.tr1984.mvvmsample.data.source.remote

import com.tr1984.mvvmsample.data.Food
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("api/food/{food_id}")
    fun getFood(@Path("food_id") id: Int): Single<Food>
}