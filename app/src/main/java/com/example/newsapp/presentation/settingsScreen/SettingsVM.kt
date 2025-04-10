package com.example.newsapp.presentation.settingsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsVM @Inject constructor(
    val settingsDb: SettingsRepository
) : ViewModel() {

    private var _moodType = MutableStateFlow<Boolean>(false)
    val moodType = _moodType.asStateFlow()

    init {
        getMood(settingsDb)
    }

    fun getMood(
        settingsDb: SettingsRepository
    ) {
        viewModelScope.launch {
            _moodType.value = settingsDb.darkMode.first()
        }

    }

    fun toggleTheme(
        isDark: Boolean,
        settingsDb: SettingsRepository
    ) {
        _moodType.value = !isDark
        viewModelScope.launch {
            settingsDb.saveDarkMode(!isDark)
        }
    }
}
