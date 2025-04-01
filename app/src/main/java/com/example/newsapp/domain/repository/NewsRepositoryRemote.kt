package com.example.newsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.models.NewArticle
import com.example.newsapp.data.models.NewsResponseDto

interface NewsRepositoryRemote{
    suspend fun getHeadLinesNews(countryCode: String, page: Int): NewsResponseDto
    suspend fun getSearchForNews(searchQuery: String, page: Int): NewsResponseDto
}