package com.example.newsapp.data.models

data class NewsResponseDto(
    val articles: List<NewArticle>,
    val status: String,
    val totalResults: Int
)