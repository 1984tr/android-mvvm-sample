package com.tr1984.mvvmsample.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tr1984.mvvmsample.data.Food
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getFoods(): Observable<Food>

    @Query("SELECT * FROM foods WHERE id = :id")
    fun getFood(id: Long): Single<Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: Food) : Completable

    @Query("DELETE FROM foods WHERE id = :id")
    fun delete(id: Long) : Completable

    @Query("DELETE FROM foods")
    fun deleteAll() : Completable
}