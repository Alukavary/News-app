package com.example.newsapp.data.repository

import com.example.newsapp.data.local.db.ArticleDatabase
import com.example.newsapp.data.models.Article
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryLocalImpl @Inject constructor(
    val db: ArticleDatabase,
): NewsRepositoryLocal {

    override fun getArticlesByCategory(category: String
    ): Flow<List<ArticleModel>> {
        return db.getArticleDao().getArticlesByCategoryFromDb(category)
    }

    override suspend fun upsetCachedArticle(articles: List<Article>) {
        db.getArticleDao().upsetCachedArticle(articles)
    }

    override fun getArticlesIsFavoriteFromDb(): Flow<List<ArticleModel>> {
        return db.getArticleDao().getArticlesIsFavoriteFromDb()
    }

    override suspend fun resetFavoriteFalse(isFavorite: Boolean, title: String) {
        return db.getArticleDao().resetFavoriteFalse(isFavorite, title)
    }

    override suspend fun deleteCachedCategory(category: String) {
        db.getArticleDao().deleteCachedCategory(category)
    }

    override suspend fun deleteIsFavCategory() {
        db.getArticleDao().deleteIsFavCategory()
    }
}