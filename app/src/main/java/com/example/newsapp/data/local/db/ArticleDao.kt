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

     @Query("SELECT * FROM articles_cached WHERE category = :category")
     fun getArticlesByCategoryFromDb(category: String): Flow<List<Article>>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun upsetCachedArticle(articles: List<Article>)

     @Query("DELETE FROM articles_cached WHERE category = :category")
     suspend fun deleteCachedCategory(category: String)

     @Query("UPDATE articles_cached SET isFavorite = :isFavorite WHERE title = :title")
     suspend fun resetFavoriteFalse(isFavorite: Boolean, title: String)

     @Query("SELECT * FROM articles_cached WHERE isFavorite = :isFavorite = 1")
     fun getArticlesIsFavoriteFromDb(isFavorite: Boolean): Flow<List<Article>>
}

//
//     @Insert(onConflict = OnConflictStrategy.REPLACE)
//     suspend fun upset(article:NewsArticle):Long
//
//     @Query("SELECT * FROM articles")
////     fun getAllArticles(): LiveData<List<NewArticle>>
////     suspend fun getAllArticles(): List<NewArticle>
//     fun getAllArticles(): Flow<List<NewsArticle>>
//
//     @Query("SELECT * FROM articles WHERE title = :title LIMIT 1")
//     suspend fun getAllArticleByID(title: String): NewsArticle?
//
//
//
//     @Delete
//     suspend fun deleteArticle(article: NewsArticle)
//}