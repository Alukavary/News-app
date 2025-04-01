package com.example.newsapp.domain.model.mapper

import android.util.Log
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.data.models.Source
import com.example.newsapp.data.models.NewArticle
import java.text.SimpleDateFormat
import java.util.Locale


fun toDomain(
    data: NewArticle
): Article {
    val id: Int? = data.id
    val defaultTitle = "Breaking news!"
    val defaultDescription = "Your ad could be here!"
    val defaultContent = "We don't know what it is if we knew but we don't know.."
    val defaultImage = R.drawable.fake_news_icon
    val defaultData = "Some time ago"
    val defaultAuthor = "Jane Doe"
    val defaultSourceName = "Oxford scholars"

    val title = data.title ?: defaultTitle
    val content = data.content ?: defaultContent
    val description = data.description ?: defaultDescription
//    val image = data.urlToImage ?: defaultImage
    val image = data.urlToImage ?: ""
    var publishedAt = data.publishedAt
    val author = data.author ?: defaultAuthor
    val url = data.url

    var publishedData = try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dataTime = inputFormat.parse(publishedAt ?: "") ?: throw IllegalArgumentException()
        outputFormat.format(dataTime)
    } catch (e: Exception) {
        defaultData
    }

    return Article(
        id,
        author,
        content,
        description,
        publishedData,
        defaultSourceName,
        title,
        url,
        image
    )

}

fun toEntity(article: Article): NewArticle {
    Log.d("MyLog", "Saving publishedAt: ${article.publishedAt}")

    return NewArticle(
        id = article.id,
        author = article.author,
        content = article.content,
        description = article.description,
        publishedAt = article.publishedAt,
        source = Source(id = "", name = article.sourceName),
        title = article.title,
        url = article.url,
        urlToImage = article.urlToImage
    )

}

