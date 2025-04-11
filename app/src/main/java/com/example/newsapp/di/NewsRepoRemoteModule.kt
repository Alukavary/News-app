package com.example.newsapp.di

import com.example.newsapp.data.local.db.ArticleDao
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryRemoteImpl
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsRepoRemoteModule {

    @Provides
    @Singleton
    fun providerNews(): NewsApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)

    @Provides
    @Singleton
    fun providerNewsRepositoryRemote(
        api: NewsApi,
        dao: ArticleDao
    ): NewsRepositoryRemote {
        return NewsRepositoryRemoteImpl(api,dao)
    }
}