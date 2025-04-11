package com.example.newsapp.domain.model.mapper

import android.util.Log
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.api.Source
import com.example.newsapp.data.models.api.NewsArticle
import com.example.newsapp.domain.model.ArticleModel
import java.text.SimpleDateFormat
import java.util.Locale


private const val INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val OUTPUT_FORMAT = "yyyy-MM-dd"

fun NewsArticle.toArticleDb(category: String,
): Article {
    return Article(
        title = title ?: "",
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        category = category,
        publishedAtFormatted = publishedAt.convertTime(),
        isFavorite = false,
    )
}

private fun String.convertTime(): String {
    val inputFormat = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault())
    val outputFormat = SimpleDateFormat(OUTPUT_FORMAT, Locale.getDefault())
    return outputFormat.format(inputFormat.parse(this))
}

fun Article.toArticleModel(): ArticleModel {
    return ArticleModel(
        author = author,
        content = content,
        description = description,
        publishedAtFormatted = publishedAtFormatted,
        title = title,
        url = url,
        urlToImage = urlToImage,
        isFavorite = isFavorite,
        category = category,
    )
}

fun ArticleModel.toArticleDb(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAtFormatted = publishedAtFormatted,
        title = title,
        url = url,
        urlToImage = urlToImage,
        isFavorite = isFavorite,
        category = category,
    )
}

fun NewsArticle.toArticleModel(category:String): ArticleModel {
    return ArticleModel(
        title = title ?: "",
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        category = category,
        publishedAtFormatted = publishedAt.convertTime(),
        isFavorite = false,
    )
}

