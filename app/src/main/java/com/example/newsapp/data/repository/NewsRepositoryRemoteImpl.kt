package com.example.newsapp.data.repository

import com.example.newsapp.data.local.db.ArticleDao
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.model.mapper.toArticleDb
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryRemoteImpl @Inject constructor(
    private val api : NewsApi,
    private val myDao: ArticleDao

) : NewsRepositoryRemote {

    override suspend fun getHeadLinesNews(countryCode: String, pageNumber: Int) =
        api.getHeadlines(countryCode, pageNumber)


    override suspend fun getNewsByCategory(searchQuery:String, pageNumber: Int) = api.searchForNews(searchQuery, pageNumber)


}