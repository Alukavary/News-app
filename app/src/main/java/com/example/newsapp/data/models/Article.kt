package com.example.newsapp.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.data.models.api.Source
import java.io.Serializable

@Entity(
    tableName = "article_cached"
)
class Article(
    @PrimaryKey
    val title: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAtFormatted: String,
    val url: String,
    val urlToImage: String,
    val isFavorite: Boolean,
    val category: String,
) : Serializable