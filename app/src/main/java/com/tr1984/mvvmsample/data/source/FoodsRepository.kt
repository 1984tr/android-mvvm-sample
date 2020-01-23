package com.tr1984.mvvmsample.data.source

import android.content.Context
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.local.AppDatabase
import io.reactivex.Observable
import io.reactivex.Single

class FoodsRepository private constructor() {

    private lateinit var database: AppDatabase

    fun initialize(context: Context) {
        AppDatabase.getInstance(context)?.let {
            database  = it
        }
    }

    fun getFoods() : Observable<Food> {
        return database.getShopDao().getFoods()
    }

    fun getFood(id: Long) : Single<Food> {
        //return ApiClient.instance.api.getFood(id)
        return database.getShopDao().getFood(id)
    }

    companion object {
        val instance = FoodsRepository()
    }
}