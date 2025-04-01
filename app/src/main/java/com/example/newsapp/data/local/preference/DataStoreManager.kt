package com.example.newsapp.data.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import com.example.newsapp.utils.Constants.MAIN_SCREEN_ROUTE
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val THEME_KEY = booleanPreferencesKey("dark_mode") //dark true
        private val FILTER_KEY = stringPreferencesKey("news_filter")
        private val SCREEN_ROUT = stringPreferencesKey("screen_rout")
    }


    val darkMode: Flow<Boolean> = dataStore.data.map { pref ->
        pref[THEME_KEY] ?: false
    }

    suspend fun saveDarkMode(isDarkMode: Boolean) {
        dataStore.edit { pref ->
            pref[THEME_KEY] = isDarkMode
        }
    }


    val newsFilter: Flow<String> = dataStore.data.map { pref ->
        pref[FILTER_KEY] ?: "general"
    }

    suspend fun saveFilter(categoryFilter: String) {
        dataStore.edit { pref ->
            pref[FILTER_KEY] = categoryFilter
        }
    }


    val screenRout: Flow<String> = dataStore.data.map { pref ->
        pref[SCREEN_ROUT] ?: MAIN_SCREEN_ROUTE
    }

    suspend fun saveScreenRout(rout: String) {
        dataStore.edit { pref ->
            pref[SCREEN_ROUT] = rout
        }
    }


}
