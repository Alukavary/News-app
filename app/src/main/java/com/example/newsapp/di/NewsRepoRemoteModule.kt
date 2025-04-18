package com.example.newsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryRemoteImpl
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsRepoRemoteModule {

    @Provides
    @Singleton
    fun provideOkhttp(@ApplicationContext context : Context) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun providerNews(okhttp : OkHttpClient): NewsApi =
        Retrofit.Builder().baseUrl(BASE_URL).client(okhttp).addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)

    @Provides
    @Singleton
    fun providerNewsRepositoryRemote(
        api: NewsApi,
    ): NewsRepositoryRemote {
        return NewsRepositoryRemoteImpl(api)
    }
}