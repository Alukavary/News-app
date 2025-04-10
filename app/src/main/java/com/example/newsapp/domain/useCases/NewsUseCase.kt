package com.example.newsapp.domain.useCases

import android.util.Log
import com.example.newsapp.data.models.CategoryTime
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ErrorType
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
import javax.inject.Inject
import kotlin.collections.map

class NewsUseCase @Inject constructor(
    val localDbCached: NewsRepositoryLocal,
    val remoteRepository: NewsRepositoryRemote,
    val localDbFetchTime: NewsFetchTimeUseCase,
    val networkHelper: NetworkHelper,
) {
    operator fun invoke(
        category: String,
    ): Flow<UIState<List<ArticleModel>>> = flow {
        emit(UIState.Loading())
        val cachedFlow = localDbCached.getArticlesByCategory(category)
            .map { it.map { article -> article.toArticleModel() } }
        val cache = cachedFlow.first()
        Log.d("MyLog", "cache ${cache.size}")
        if (networkHelper.isNetworkAvailable()) {
            try {
                if (cache.isEmpty() || localDbFetchTime.shouldFetch(category)) {
                    Log.d(
                        "MyLog", "cache is empty orlocalDbFetchTime.shouldFetch${
                            localDbFetchTime.shouldFetch(category)
                        }"
                    )
                    //from api
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
                    emitAll(
                        localDbCached.getArticlesByCategory(category)
                            .map { UIState.Success(it.map { article -> article.toArticleModel() }) })
                }else{
                    emitAll(
                        cachedFlow.map { UIState.Success(it) }
                    )
                }

            } catch (e: Exception) {
                Log.d("MyLog", "error ${e.message}")
                if(cache.isEmpty())
                emit(UIState.Error("Ups, incorrect input, try again", type = ErrorType.OTHER_WITHOUT_CACHE))
                else
                    emitAll(
                        cachedFlow.map { UIState.Error(
                            "Ups, incorrect input, try again",
                            type = ErrorType.OTHER_WITH_CACHE,
                            data = it)})
            }

        } else {
            if (cache.isEmpty()) {
                emit(UIState.Error("Check the internet", type = ErrorType.NETWORK_WITHOUT_CACHE))
            } else {
                emitAll(
                    cachedFlow.map {
                        UIState.Error(
                            "Check the internet",
                            type = ErrorType.NETWORK_WITH_CACHE,
                            data = it
                        )
                    }
                )
            }
        }
    }
}


