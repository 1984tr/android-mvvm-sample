package com.tr1984.mvvmsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(@PrimaryKey(autoGenerate = true) val id: Long, val imageUrl: String)