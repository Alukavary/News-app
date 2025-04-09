package com.example.newsapp.presentation.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.useCases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SearchVM @Inject constructor(
    val newsUseCase: NewsUseCase
) : ViewModel() {

    private var _data = MutableStateFlow<UIState<List<ArticleModel>>>(UIState.Default())
    val data = _data.asStateFlow()

    val listData = data
        .map { it ->
            when (it) {
                is UIState.Success -> it.data
                else -> emptyList()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

  fun loadingCategory(
        category: String,
    ) {
        _data.value = UIState.Loading()
        viewModelScope.launch {
            newsUseCase.invoke(category).collect {
                _data.value = it
            }
        }
    }
}