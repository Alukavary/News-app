package com.example.newsapp.data.local.dbCachedTime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.CategoryTime

@Dao
interface CategoryTimeDao {

    @Query("SELECT lastFetchedMillis FROM category_fetch_time WHERE category = :category")
    suspend fun getLastCategoryTime(category:String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLastCategoryTime(fetchTime: CategoryTime)
}