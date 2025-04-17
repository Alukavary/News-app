package com.example.newsapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.Article
import com.example.newsapp.domain.model.ArticleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
     @Query("SELECT * FROM article_cached WHERE category = :category")
     fun getArticlesByCategoryFromDb(category: String): Flow<List<ArticleModel>>

     @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun upsetCachedArticle(articles: List<Article>)

     @Query("DELETE FROM article_cached WHERE category = :category AND isFavorite = 0")
     suspend fun deleteCachedCategory(category: String)

     @Query("UPDATE article_cached SET isFavorite = 0 WHERE isFavorite = 1")
     suspend fun deleteIsFavCategory()

     @Query("UPDATE article_cached SET isFavorite = :isFavorite WHERE title = :title")
     suspend fun resetFavoriteFalse(isFavorite: Boolean, title: String)

     @Query("SELECT * FROM article_cached WHERE isFavorite = 1")
     fun getArticlesIsFavoriteFromDb(): Flow<List<ArticleModel>>
}
