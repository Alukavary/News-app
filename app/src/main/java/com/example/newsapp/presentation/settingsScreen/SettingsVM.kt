package com.example.newsapp.presentation.settingsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    val settingsDb: SettingsRepository
) : ViewModel() {

    val moodType: StateFlow<Boolean> = settingsDb.darkMode
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false)

    fun toggleTheme(
        isDark: Boolean,
        settingsDb: SettingsRepository
    ) {
        viewModelScope.launch {
            settingsDb.saveDarkMode(!isDark)
        }
    }
}
