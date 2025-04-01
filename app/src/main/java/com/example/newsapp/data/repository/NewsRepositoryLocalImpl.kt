package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.local.db.ArticleDatabase
import com.example.newsapp.data.local.preference.DataStoreManager
import com.example.newsapp.data.models.NewArticle
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import javax.inject.Inject

class NewsRepositoryLocalImpl @Inject constructor(
    val db: ArticleDatabase,

): NewsRepositoryLocal {
    override suspend fun upset(article: NewArticle) = db.getArticleDao().upset(article)

    override fun getAllArticles() = db.getArticleDao().getAllArticles()
//    override suspend fun getAllArticles() = db.getArticleDao().getAllArticles()

    override suspend fun getAllArticleById(title: String) =
        db.getArticleDao().getAllArticleByID(title)

    override suspend fun deleteArticle(article: NewArticle) =
        db.getArticleDao().deleteArticle(article)
}