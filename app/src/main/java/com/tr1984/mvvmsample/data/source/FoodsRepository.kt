package com.tr1984.mvvmsample.data.source

import android.content.Context
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.local.AppDatabase
import io.reactivex.Observable

class FoodsRepository private constructor(val context: Context) {

    fun getList() : Observable<Food> {
        return AppDatabase.getInstance(context).getShopDao().getFoods()
    }

    fun get(id: Int) : Food {
        return
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