package com.example.newsapp.domain.repository

import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.api.NewsArticle
import com.example.newsapp.domain.model.ArticleModel
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryLocal {

    fun getArticlesByCategory(category: String): Flow<List<Article>>

    suspend fun upsetCachedArticle(articles: List<Article>)

    suspend fun deleteCachedCategory(category:String)

    suspend fun deleteIsFavCategory(isFavorite: Boolean)

    fun getArticlesIsFavoriteFromDb():Flow<List<Article>>

    suspend fun resetFavoriteFalse(isFavorite: Boolean, title:String)

}