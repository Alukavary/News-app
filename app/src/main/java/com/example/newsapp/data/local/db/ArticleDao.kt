package com.example.newsapp.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.api.NewsArticle
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

     @Query("SELECT * FROM article_cached WHERE category = :category")
     fun getArticlesByCategoryFromDb(category: String): Flow<List<Article>>

     @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun upsetCachedArticle(articles: List<Article>)

     @Query("DELETE FROM article_cached WHERE category = :category")
     suspend fun deleteCachedCategory(category: String)

     @Query("DELETE FROM article_cached WHERE isFavorite = :isFavorite")
     suspend fun deleteIsFavCategory(isFavorite: Boolean)

     @Query("UPDATE article_cached SET isFavorite = :isFavorite WHERE title = :title")
     suspend fun resetFavoriteFalse(isFavorite: Boolean, title: String)

     @Query("SELECT * FROM article_cached WHERE isFavorite = 1")
     fun getArticlesIsFavoriteFromDb(): Flow<List<Article>>
}
