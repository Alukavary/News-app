package com.example.newsapp.domain.useCases

import com.example.newsapp.domain.repository.NewsCategoryFetchTime
import jakarta.inject.Inject

class NewsFetchTimeUseCase @Inject constructor(
    val db: NewsCategoryFetchTime
) {
    suspend fun shouldFetch(category:String): Boolean{
        val lastFetchTime = db.getLastCategoryTime(category)
        val now  = System.currentTimeMillis()
        return (lastFetchTime == null || (now - lastFetchTime) > 30*60*1000)
    }
}