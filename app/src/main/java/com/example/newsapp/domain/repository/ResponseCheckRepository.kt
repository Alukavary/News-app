package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import kotlinx.coroutines.flow.Flow

interface ResponseCheckRepository{
    fun observerCategory(
        category: String)
    : Flow<UIState<List<ArticleModel>>>

    suspend fun refreshNews(
        category: String)

    suspend fun searchNews(
        query: String)
    : Flow<UIState<List<ArticleModel>>>
}