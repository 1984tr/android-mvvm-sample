package com.tr1984.mvvmsample.data.source

import android.content.Context
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.local.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single

class FoodsRepository private constructor() {

    private lateinit var context: Context

    private val database: AppDatabase? by lazy {
        AppDatabase.getInstance(context)
    }

    fun initialize(context: Context) {
        this.context = context
    }

    fun getFoods() : Single<List<Food>>? {
        return database?.getFoodDao()?.getFoods()
    }

    fun getFoods(isFavorite: Boolean) : Single<List<Food>>? {
        return database?.getFoodDao()?.getFoods(isFavorite)
    }

    fun postFoods(foods: List<Food>) : Completable? {
        return database?.getFoodDao()?.insertAll(foods)
    }

    fun putFood(id: Long, isFavorite: Boolean) : Completable? {
        return database?.getFoodDao()?.update(id, isFavorite)
    }

    fun getFood(id: Long) : Single<Food>? {
        //return ApiClient.instance.api.getFood(id)
        return database?.getFoodDao()?.getFood(id)
    }

    companion object {
        val instance = FoodsRepository()
    }
}