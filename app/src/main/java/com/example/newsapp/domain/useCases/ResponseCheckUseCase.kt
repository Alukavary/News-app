package com.example.newsapp.domain.useCases

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.repository.ResponseCheckRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: ResponseCheckRepository
) {
    fun observe(category: String): Flow<UIState<List<ArticleModel>>> {
        return repository.observerCategory(category)
    }

    suspend fun invokeSearchNews(category: String): Flow<UIState<List<ArticleModel>>> {
        return repository.searchNews(category)
    }

    suspend fun refresh (category: String){
        repository.refreshNews(category)
    }
}