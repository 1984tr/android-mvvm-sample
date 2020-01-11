package com.tr1984.mvvmsample.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tr1984.mvvmsample.data.Shop

@Database(entities = arrayOf(Shop::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getShopDao(): ShopDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "mvvm_sample.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}