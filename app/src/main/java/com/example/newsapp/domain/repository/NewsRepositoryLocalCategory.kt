package com.example.newsapp.domain.repository

import com.example.newsapp.data.models.CategoryTime

interface NewsRepositoryLocalCategory {

    suspend fun getLastCategoryTime(category: String): Long?

    suspend fun saveLastCategoryTime(fetchTime: CategoryTime)
}
