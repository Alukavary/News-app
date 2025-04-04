package com.example.newsapp.data.models.api

class NewsResponseDto(
    val articles: List<NewsArticle>,
    val status: String,
    val totalResults: Int
)