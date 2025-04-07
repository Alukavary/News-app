package com.example.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_fetch_time")

class CategoryTime (
    @PrimaryKey
    val category: String,
    val lastFetchedMillis: Long

)
