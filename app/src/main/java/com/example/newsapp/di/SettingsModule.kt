package com.example.newsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.data.local.preference.DataStoreManager
import com.example.newsapp.data.repository.SettingsRepositoryImpl
import com.example.newsapp.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object SettingsModule {

    private val Context.dataStore by preferencesDataStore("data_store")
    @Provides
    @Singleton
    fun providerPreferenceManager(@ApplicationContext context: Context): DataStore<Preferences>{
        return context.dataStore
    }


    @Provides
    @Singleton
    fun providerSettingsRepository(
        datastoreManager: DataStoreManager
    ): SettingsRepository{
        return SettingsRepositoryImpl(datastoreManager)
    }
}