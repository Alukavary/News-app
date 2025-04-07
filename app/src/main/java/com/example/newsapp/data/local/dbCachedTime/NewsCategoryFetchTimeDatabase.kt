package com.example.newsapp.data.local.dbCachedTime

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.local.db.ArticleDatabase
import com.example.newsapp.data.models.CategoryTime


@Database(
    entities = [CategoryTime::class], version = 1
)

abstract class NewsCategoryFetchTimeDatabase: RoomDatabase() {
    abstract fun getCategoryTimeDao(): CategoryTimeDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ArticleDatabase::class.java, "category_fetch_time.db"
        ).build()
    }
}