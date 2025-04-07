package com.example.newsapp.domain.useCases

import android.util.Log
import com.example.newsapp.domain.repository.NewsCategoryFetchTime
import jakarta.inject.Inject

class NewsFetchTimeUseCase @Inject constructor(
    val db: NewsCategoryFetchTime
) {
    suspend fun shouldFetch(category:String): Boolean{
        val lastFetchTime = db.getLastCategoryTime(category)
        val now  = System.currentTimeMillis()
Log.d("MyLog", "shouldFetch lastFetchTime $lastFetchTime and  now $now")
        return (lastFetchTime == null || (now - lastFetchTime) > 30*60*1000)
    }
}