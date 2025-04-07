package com.example.newsapp.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.CategoryTime

interface NewsCategoryFetchTime {

    suspend fun getLastCategoryTime(category: String): Long?

    suspend fun saveLastCategoryTime(fetchTime: CategoryTime)
}