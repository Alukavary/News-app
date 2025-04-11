package com.example.newsapp.presentation.newsScreen

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.ItemType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.repository.SettingsRepository
import com.example.newsapp.domain.useCases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NewsVM @Inject constructor(
    val newsUseCase: NewsUseCase,
) : ViewModel() {

    private var _isRefresh = MutableStateFlow<Boolean>(false)
    val isRefresh = _isRefresh.asStateFlow()

    private var _data = MutableStateFlow<UIState<List<ArticleModel>>>(UIState.Default)
    val data = _data.asStateFlow()

    private var _selectedCategory = MutableStateFlow<String>("Business")
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        loadingCategory(_selectedCategory.value)
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
        _data.value = UIState.Loading
        _selectedCategory.value = category
        viewModelScope.launch {
            newsUseCase.invoke(category).collect {
                _data.value = it
            }
        }
    }

    fun isRefreshData(
        category: String
    ) {
        _data.value = UIState.Loading
        viewModelScope.launch {
            _isRefresh.value = true
            newsUseCase.invoke(category).collect {
                _data.value = it
                _isRefresh.value = false

            }

        }
    }
}