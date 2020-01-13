package com.tr1984.mvvmsample.data.source

import android.content.Context
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.local.AppDatabase
import com.tr1984.mvvmsample.data.source.remote.ApiClient
import io.reactivex.Observable
import io.reactivex.Single

class FoodsRepository private constructor(val context: Context) {

    fun getList() : Observable<Food>? {
        return AppDatabase.getInstance(context)?.getShopDao()?.getFoods()
    }

    fun get(id: Int) : Single<Food> {
        return ApiClient.instance.api.getFood(id)
    }

    companion object {
        private var instance: FoodsRepository? = null

        fun getInstance(context: Context): FoodsRepository? {
            if (instance == null) {
                synchronized(FoodsRepository::class) {
                    instance = FoodsRepository(context)
                }
            }
            return instance
        }
    }
}