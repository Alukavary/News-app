package com.example.newsapp.presentation.newsScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.mapper.toDomain
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NewsVM @Inject constructor(
    private val newsRepository: NewsRepositoryRemote,
    val localDb: SettingsRepository

): ViewModel() {


    private var _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>> = _data

    var selectedCategory by mutableStateOf("general")

    init {
        getDataHeadLines()

    }

    fun getDataHeadLines(){
        viewModelScope.launch {
            val map = newsRepository.getHeadLinesNews("US", 1).articles.map { toDomain(it) }

            _data.value = map
        }
    }

    fun getDataSearchForNews() {
        Log.d("MyLod", " мы гет дата")
        viewModelScope.launch {
            val map =
                newsRepository.getSearchForNews(selectedCategory, 1).articles.map { toDomain(it) }
            _data.value = map

        }
    }

    fun getNewCategory(newCategory: String) {
        selectedCategory = newCategory
        viewModelScope.launch {
            localDb.saveFilter(selectedCategory)
            getDataSearchForNews()
        }
    }

    fun start(){
        viewModelScope.launch {
            localDb.newsFilter.collect { category ->
                selectedCategory = category
            }
            getNewCategory(selectedCategory)
        }

    }

    fun getCategory(){
        viewModelScope.launch {
            localDb.newsFilter.collect { category ->
                selectedCategory = category
            }
        }
    }

}