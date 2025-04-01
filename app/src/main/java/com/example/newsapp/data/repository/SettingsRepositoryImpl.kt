package com.example.newsapp.data.repository

import com.example.newsapp.data.local.preference.DataStoreManager
import com.example.newsapp.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val datastoreManager: DataStoreManager): SettingsRepository {

    override val darkMode = datastoreManager.darkMode
    override val newsFilter = datastoreManager.newsFilter
    override val screenRout = datastoreManager.screenRout

    override suspend fun saveDarkMode(isDarkMode: Boolean) =datastoreManager.saveDarkMode(isDarkMode)
    override suspend fun saveFilter(categoryFilter: String) = datastoreManager.saveFilter(categoryFilter)
    override suspend fun saveScreenRout(rout: String) = datastoreManager.saveScreenRout(rout)
}