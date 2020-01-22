package com.tr1984.mvvmsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(@PrimaryKey(autoGenerate = true) val id: Long, val imageUrl: String, val name: String = "", val isFavorite: Boolean = false) {

    companion object {
        val dummy = Food(1, "https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466__480.jpg", "food-1932466", true)
    }
}