package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.models.CategoryTime
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ErrorType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.model.mapper.toArticleDb
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.domain.useCases.NetworkHelper
import com.example.newsapp.domain.useCases.NewsFetchTimeUseCase
import com.example.newsapp.domain.repository.ResponseCheckRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResponseCheckRepositoryImpl @Inject constructor(
    val localDbCached: NewsRepositoryLocal,
    val remoteRepository: NewsRepositoryRemote,
    val localDbFetchTime: NewsFetchTimeUseCase,
    val networkHelper: NetworkHelper,
): ResponseCheckRepository{
    override fun getNewsByCategory(category: String): Flow<UIState<List<ArticleModel>>> = flow {
        val cachedFlow = localDbCached.getArticlesByCategory(category)
        val cache = cachedFlow.first()

        if (networkHelper.isNetworkAvailable()) {
            try {
                if (cache.isEmpty() || localDbFetchTime.shouldFetch(category)) {
                    //from api
                    val response = remoteRepository.getNewsByCategory(category, 1)
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
                    val dataFromDb = localDbCached.getArticlesByCategory(category).first()
                    emit(UIState.Success(dataFromDb))
                } else {
                    emit(
                        UIState.Success(cache)
                    )
                }
            } catch (e: Exception) {
                Log.d("MyLog", "e $e")
                if (cache.isEmpty()) emit(
                    UIState.Error(
                        "Ups, incorrect input, try again",
                        type = ErrorType.OTHER_WITHOUT_CACHE
                    )
                )
                else emit(
                    UIState.Error(
                        "Ups, incorrect input, try again",
                        type = ErrorType.OTHER_WITH_CACHE,
                        data = cache
                    )
                )
            }

        } else {
            if (cache.isEmpty()) {
                emit(UIState.Error("Check the internet", type = ErrorType.NETWORK_WITHOUT_CACHE))
            } else {
                emit(
                    UIState.Error(
                        "Check the internet", type = ErrorType.NETWORK_WITH_CACHE, data = cache
                    )
                )
            }
        }
    }
}