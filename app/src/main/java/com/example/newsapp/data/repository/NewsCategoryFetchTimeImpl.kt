package com.example.newsapp.data.repository

import com.example.newsapp.data.local.dbCachedTime.NewsCategoryFetchTimeDatabase
import com.example.newsapp.data.models.CategoryTime
import com.example.newsapp.domain.repository.NewsCategoryFetchTime
import jakarta.inject.Inject

class NewsCategoryFetchTimeImpl @Inject constructor(
    val db: NewsCategoryFetchTimeDatabase
) : NewsCategoryFetchTime {

    override suspend fun getLastCategoryTime(category: String): Long? {
        return db.getCategoryTimeDao().getLastCategoryTime(category)
    }

    override suspend fun saveLastCategoryTime(fetchTime: CategoryTime) {
        return db.getCategoryTimeDao().saveLastCategoryTime(fetchTime)
    }
}