package com.example.newsapp.domain.useCases

import android.util.Log
import com.example.newsapp.data.models.CategoryTime
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.model.mapper.toArticleDb
import com.example.newsapp.domain.model.mapper.toArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import kotlin.collections.map

class NewsUseCase @Inject constructor(
    val localDbCached: NewsRepositoryLocal,
    val remoteRepository: NewsRepositoryRemote,
    val localDbFetchTime: NewsFetchTimeUseCase
) {
    operator fun invoke(
        category: String,
    ): Flow<UIState<List<ArticleModel>>> = flow {
        emit(UIState.Loading())
        try {
            val cachedFlow = localDbCached.getArticlesByCategory(category)
                .map { it.map { article -> article.toArticleModel() } }
            val cache = cachedFlow.first()
            Log.d("MyLog", "cache ${cache.size}")

            if (cache.isEmpty() || localDbFetchTime.shouldFetch(category)) {
                Log.d(
                    "MyLog", "cache is empty orlocalDbFetchTime.shouldFetch${
                        localDbFetchTime.shouldFetch(category)}"
                )
                val response = remoteRepository.getNewsByCategory(category, 1)
                Log.d("MyLog", "response ${response.articles.size}")

                //to db
                localDbCached.upsetCachedArticle(
                    response.articles.map {
                        it.toArticleDb(category)
                    })

                localDbFetchTime.db.saveLastCategoryTime(
                    CategoryTime(
                        category, System.currentTimeMillis()
                    )
                )
            }
            emitAll(
                localDbCached.getArticlesByCategory(category)
                    .map { UIState.Success(it.map { article -> article.toArticleModel() }) })
        } catch (e: IOException) {
            Log.d("MyLog", "error ${e.message}")
            emit(UIState.Error("No signal"))
        }catch (e: Exception) {
            Log.d("MyLog", "error ${e.message}")
            emit(UIState.Error("Ups, incorrect input, try again"))
        }
    }

//        return localDbCached.getArticlesByCategory(category)
//            .map { it.map { article -> article.toArticleModel() } }
//            .map { UIState.Success(it) as UIState<List<ArticleModel>> }
//            .onStart {
//                emit(UIState.Loading())
//                try {
//                    //from api
//                    val response = remoteRepository.getNewsByCategory(category, 1)
//                    Log.d("MyLog", "response ${response.articles.size}")
//
//                    //to db
//                    localDbCached.upsetCachedArticle(
//                        response.articles.map {
//                            it.toArticleDb(category)
//                        })
//                } catch (e: Exception) {
//                    Log.d("MyLog", "error ${e.message}")
//                    emit(UIState.Error())
//                }
//            }
}


