package com.example.newsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.models.NewArticle
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryLocal {

    suspend fun upset(article: NewArticle):Long
//    fun getAllArticles(): LiveData<List<NewArticle>>
//    suspend fun getAllArticles(): List<NewArticle>
    fun getAllArticles(): Flow<List<NewArticle>>
    suspend fun getAllArticleById(title:String): NewArticle?
    suspend fun deleteArticle(article: NewArticle)
}