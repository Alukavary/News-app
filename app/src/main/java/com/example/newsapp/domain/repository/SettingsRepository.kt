package com.example.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val darkMode: Flow<Boolean>
    val newsFilter: Flow<String>
    val screenRout: Flow<String>

    suspend fun saveDarkMode(isDarkMode: Boolean)
    suspend fun saveFilter(categoryFilter: String)
    suspend fun saveScreenRout(rout: String)
}