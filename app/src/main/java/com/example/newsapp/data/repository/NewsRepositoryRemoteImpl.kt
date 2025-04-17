package com.example.newsapp.data.repository

import com.example.newsapp.data.models.api.NewsResponseDto
import com.example.newsapp.data.remote.api.NewsApi
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import javax.inject.Inject

class NewsRepositoryRemoteImpl @Inject constructor(
    private val api: NewsApi,

    ) : NewsRepositoryRemote {

    override suspend fun getHeadLinesNews(countryCode: String, pageNumber: Int) =
        api.getHeadlines(countryCode, pageNumber)

    override suspend fun getNewsByCategory(searchQuery: String, pageNumber: Int): NewsResponseDto =
        api.searchForNews(searchQuery, pageNumber)
}
