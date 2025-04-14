package com.example.newsapp.data.repository

import com.example.newsapp.data.local.dbCachedTime.NewsCategoryFetchTimeDatabase
import com.example.newsapp.data.models.CategoryTime
import com.example.newsapp.domain.repository.NewsRepositoryLocalCategory
import javax.inject.Inject

class NewsRepositoryLocalCategoryImpl @Inject constructor(
    val db: NewsCategoryFetchTimeDatabase
) : NewsRepositoryLocalCategory {

    override suspend fun getLastCategoryTime(category: String): Long? {
        return db.getCategoryTimeDao().getLastCategoryTime(category)
    }

    override suspend fun saveLastCategoryTime(fetchTime: CategoryTime) {
        return db.getCategoryTimeDao().saveLastCategoryTime(fetchTime)
    }
}