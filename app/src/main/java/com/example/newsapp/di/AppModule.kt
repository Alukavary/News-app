package com.example.newsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.newsapp.data.local.db.ArticleDao
import com.example.newsapp.data.local.db.ArticleDatabase
import com.example.newsapp.data.local.preference.DataStoreManager
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryLocalImpl
import com.example.newsapp.data.repository.NewsRepositoryRemoteImpl
import com.example.newsapp.data.repository.SettingsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.domain.repository.SettingsRepository
import com.example.newsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    private val Context.dataStore by preferencesDataStore("data_store")
    @Provides
    @Singleton
    fun providerPreferenceManager(@ApplicationContext context: Context): DataStore<Preferences>{
        return context.dataStore
    }


    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "articles_cached_db.db"
        ).build()


    @Provides
    @Singleton
    fun providerDao(db: ArticleDatabase): ArticleDao = db.getArticleDao()


    @Provides
    @Singleton
    fun providerNews(): NewsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)



    @Provides
    @Singleton
    fun providerNewsRepositoryRemote(
        api: NewsApi
    ): NewsRepositoryRemote{
        return NewsRepositoryRemoteImpl(api)
    }


    @Provides
    @Singleton
    fun providerNewsRepositoryLocal(
        db: ArticleDatabase
    ): NewsRepositoryLocal{
        return NewsRepositoryLocalImpl(db)
    }



    @Provides
    @Singleton
    fun providerSettingsRepository(
        datastoreManager: DataStoreManager
    ): SettingsRepository{
        return SettingsRepositoryImpl(datastoreManager)
    }
}