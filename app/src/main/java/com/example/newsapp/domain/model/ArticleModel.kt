package com.example.newsapp.domain.model

import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
data class ArticleModel(
    val author: String,
    val content: String,
    val description: String,
    val publishedAtFormatted: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val isFavorite: Boolean,
    val category: String,
)