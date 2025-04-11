package com.example.newsapp.domain.repository

import com.example.newsapp.data.models.api.NewsResponseDto

interface NewsRepositoryRemote{
    suspend fun getHeadLinesNews(countryCode: String, page: Int): NewsResponseDto
    suspend fun getNewsByCategory(searchQuery: String, page: Int): NewsResponseDto
//    suspend fun getNewsByCategoryPart(searchQuery: String, page: Int)
}