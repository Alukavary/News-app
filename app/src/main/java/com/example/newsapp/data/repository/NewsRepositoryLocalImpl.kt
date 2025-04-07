package com.example.newsapp.data.repository

import com.example.newsapp.data.local.db.ArticleDatabase
import com.example.newsapp.data.models.Article
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryLocalImpl @Inject constructor(
    val db: ArticleDatabase,
): NewsRepositoryLocal {

    override fun getArticlesByCategory(category: String
    ): Flow<List<Article>> {
        return db.getArticleDao().getArticlesByCategoryFromDb(category)
    }

    override suspend fun upsetCachedArticle(articles: List<Article>) {
        db.getArticleDao().upsetCachedArticle(articles)
    }

    override fun getArticlesIsFavoriteFromDb(): Flow<List<Article>> {
        return db.getArticleDao().getArticlesIsFavoriteFromDb()
    }

    override suspend fun resetFavoriteFalse(isFavorite: Boolean, title: String) {
        return db.getArticleDao().resetFavoriteFalse(isFavorite, title)
    }

    override suspend fun deleteCachedCategory(category: String) {
        db.getArticleDao().deleteCachedCategory(category)
    }

    override suspend fun deleteIsFavCategory(isFavorite: Boolean) {
        db.getArticleDao().deleteIsFavCategory(isFavorite)
    }
}