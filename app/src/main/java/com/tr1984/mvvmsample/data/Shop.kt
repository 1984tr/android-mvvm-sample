package com.tr1984.mvvmsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shops")
data class Shop(@PrimaryKey(autoGenerate = true) val id: Long, val name: String, var contact: String) {
}