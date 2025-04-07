package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.dbCachedTime.CategoryTimeDao
import com.example.newsapp.data.local.dbCachedTime.NewsCategoryFetchTimeDatabase
import com.example.newsapp.data.repository.NewsCategoryFetchTimeImpl
import com.example.newsapp.domain.repository.NewsCategoryFetchTime
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsCategoryFetchModule {

    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext context: Context): NewsCategoryFetchTimeDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            NewsCategoryFetchTimeDatabase::class.java,
            "category_fetch_time.db"
        ).build()

    @Provides
    @Singleton
    fun providerDao(db: NewsCategoryFetchTimeDatabase): CategoryTimeDao {
        return db.getCategoryTimeDao()
    }

    @Provides
    @Singleton
    fun providerNewsReposFetchTime(
        db: NewsCategoryFetchTimeDatabase
    ): NewsCategoryFetchTime {
        return NewsCategoryFetchTimeImpl(db)
    }
}