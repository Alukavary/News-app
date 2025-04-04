package com.example.newsapp.presentation.newsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.domain.repository.SettingsRepository
import com.example.newsapp.domain.useCase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NewsVM @Inject constructor(
    private val newsRepository: NewsRepositoryRemote,
    val settingsDb: SettingsRepository,
    val newsUseCase: NewsUseCase

): ViewModel() {
    private var _data = MutableStateFlow<UIState<List<ArticleModel>>>(UIState.Loading())
    val data = _data.asStateFlow()

    private var _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        viewModelScope.launch {
            settingsDb.newsFilter.collect { category ->
                _selectedCategory.value = category
                loadingCategory(category)
                Log.d("MyLog", "init")
            }
        }
    }

//
//    init {
//        getDataHeadLines()
//    }

//    fun getDataHeadLines(){
//        viewModelScope.launch {
//            val map = newsRepository.getHeadLinesNews("US", 1).articles.map { toDomain(it) }
//            _data.value = map
//        }
//    }

//    fun loadingNews() {
//        Log.d("MyLod", " мы гет дата")
//        viewModelScope.launch {
//            val map =
//                newsRepository.getNewsByCategory(selectedCategory, 1).articles.map { toDomain(it) }
//            _data.value = map
//        }
//    }
    fun loadingCategory(
        category: String,
    ) {
    Log.d("MyLog", " in NewsVM until loadingCategory")

    viewModelScope.launch {
            newsUseCase.invoke(category).collect {
                _data.value = it
                Log.d("Mylog", " loading data ${it}")
            }
        }
    }
}