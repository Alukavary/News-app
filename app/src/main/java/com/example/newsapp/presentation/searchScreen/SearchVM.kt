package com.example.newsapp.presentation.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.useCases.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchVM @Inject constructor(
    val newsUseCase: NewsUseCase
) : ViewModel() {

    private var _data = MutableStateFlow<UIState<List<ArticleModel>>>(UIState.Empty)
    val data = _data.asStateFlow()

    fun loadingCategory(
        category: String,
    ) {
        _data.value = UIState.Loading
        viewModelScope.launch {
            newsUseCase.invokeSearchNews(category).collect {
                _data.value = it
            }
        }
    }
}