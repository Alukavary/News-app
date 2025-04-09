package com.example.newsapp.presentation.newsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.ItemType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.repository.SettingsRepository
import com.example.newsapp.domain.useCases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class NewsVM @Inject constructor(
    val settingsDb: SettingsRepository,
    val newsUseCase: NewsUseCase

) : ViewModel() {

    private var _data = MutableStateFlow<UIState<List<ArticleModel>>>(UIState.Loading())
    val data = _data.asStateFlow()

    private var _isRefresh = MutableStateFlow<Boolean>(false)
    val isRefresh = _isRefresh.asStateFlow()


    private var _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        viewModelScope.launch {
            settingsDb.newsFilter
                .distinctUntilChanged()
                .collect { category ->
                    _selectedCategory.value = category
                    loadingCategory(category)
                    Log.d("MyLog", "init")
                }
        }
    }

    fun generateItem(data: List<ArticleModel>?): List<GenerateItem> {
        val createItemList = mutableListOf<GenerateItem>()
        if (data != null) {
            for (i in data.indices) {
                if (i == 0 || i % 5 == 0) createItemList.add(GenerateItem(ItemType.TYPE1, data[i]))
                else createItemList.add(GenerateItem(ItemType.TYPE2, data[i]))
            }
            return createItemList
        }
        return emptyList()
    }

    fun loadingCategory(
        category: String,
    ) {
        viewModelScope.launch {
            settingsDb.saveFilter(category)
            _isRefresh.value = true
            newsUseCase.invoke(category).collect {
                _data.value = it
                _isRefresh.value = false
            }
        }
    }
}