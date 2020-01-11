package com.tr1984.mvvmsample.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tr1984.mvvmsample.data.Shop
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ShopDao {

    @Query("SELECT * FROM SHOPS")
    fun getShops(): Observable<Shop>

    @Query("SELECT * FROM shops WHERE id = :id")
    fun getShop(id: Long): Single<Shop>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shop: Shop) : Completable

    @Query("DELETE FROM shops WHERE id = :id")
    fun delete(id: Long) : Completable

    @Query("DELETE FROM shops")
    fun deleteAll() : Completable
}