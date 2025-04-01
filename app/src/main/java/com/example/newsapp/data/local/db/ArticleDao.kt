package com.example.newsapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.NewArticle
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun upset(article:NewArticle):Long

     @Query("SELECT * FROM articles")
//     fun getAllArticles(): LiveData<List<NewArticle>>
//     suspend fun getAllArticles(): List<NewArticle>
     fun getAllArticles(): Flow<List<NewArticle>>

     @Query("SELECT * FROM articles WHERE title = :title LIMIT 1")
     suspend fun getAllArticleByID(title: String): NewArticle?



     @Delete
     suspend fun deleteArticle(article: NewArticle)
}