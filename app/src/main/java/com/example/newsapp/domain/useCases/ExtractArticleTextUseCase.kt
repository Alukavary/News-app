package com.example.newsapp.domain.useCases

import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class ExtractArticleTextUseCase @Inject constructor(
) {
    suspend operator fun invoke (url: String): String{
        return withContext(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect(url).get()

                val articleElement = doc.select("article").first()
                    ?: doc.select("div[class*=content]").first()
                    ?: doc.body()

                articleElement.text()
            } catch (e: Exception) {
                "Error loading article, check the internet"
            }
        }
    }
}