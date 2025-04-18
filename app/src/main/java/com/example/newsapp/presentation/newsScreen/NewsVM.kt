package com.example.newsapp.presentation.newsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.GenerateItem
import com.example.newsapp.domain.model.ItemType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.useCases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NewsVM @Inject constructor(
    val newsUseCase: NewsUseCase,
) : ViewModel() {

    private var _isRefresh = MutableStateFlow<Boolean>(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _refreshTrigger = MutableSharedFlow<String>(replay = 1)
    val refreshTrigger = _refreshTrigger.asSharedFlow()

    private var _selectedCategory = MutableStateFlow<String>("Business")
    val selectedCategory = _selectedCategory.asStateFlow()

    val data: StateFlow<UIState<List<ArticleModel>>> =
        refreshTrigger
            .flatMapLatest { category ->
                flow {
                    emit(UIState.Loading)
                    emitAll(newsUseCase.observe(category))
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                UIState.Loading
            )

    init {
        viewModelScope.launch {
            _refreshTrigger.emit(_selectedCategory.value)
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

    fun refreshData(
        category: String
    ) {
        _selectedCategory.value = category
        viewModelScope.launch {
            _isRefresh.value = true
            newsUseCase.refresh(category)
            _refreshTrigger.emit(category)
            delay(300)
            _isRefresh.value = false
        }
    }

    fun loadingCategory(
        category: String
    ) {
        _selectedCategory.value = category
        viewModelScope.launch {
            _refreshTrigger.emit(category)
            newsUseCase.refresh(category)
        }
    }
}
