package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.db.ArticleDao
import com.example.newsapp.data.local.db.ArticleDatabase
import com.example.newsapp.data.repository.NewsRepositoryLocalImpl
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsRepoLocalModule {

    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(
            context.applicationContext, ArticleDatabase::class.java, "article_cached_db.db"
        ).build()

    @Provides
    @Singleton
    fun providerDao(db: ArticleDatabase): ArticleDao = db.getArticleDao()

    @Provides
    @Singleton
    fun providerNewsRepositoryLocal(
        db: ArticleDatabase
    ): NewsRepositoryLocal {
        return NewsRepositoryLocalImpl(db)
    }
}