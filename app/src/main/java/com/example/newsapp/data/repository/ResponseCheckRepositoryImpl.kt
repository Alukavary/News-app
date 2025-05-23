package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.models.CategoryTime
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.ErrorType
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.model.mapper.toArticleDb
import com.example.newsapp.domain.model.mapper.toArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import com.example.newsapp.domain.useCases.NetworkHelper
import com.example.newsapp.domain.useCases.NewsFetchTimeUseCase
import com.example.newsapp.domain.repository.ResponseCheckRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class ResponseCheckRepositoryImpl @Inject constructor(
    val localDbCached: NewsRepositoryLocal,
    val remoteRepository: NewsRepositoryRemote,
    val localDbFetchTime: NewsFetchTimeUseCase,
    val networkHelper: NetworkHelper,
) : ResponseCheckRepository {

    override fun observerCategory(category: String): Flow<UIState<List<ArticleModel>>> {
        return localDbCached.getArticlesByCategory(category).map { list ->
            if (list.isEmpty()) {
                if (!networkHelper.isNetworkAvailable()) {
                    UIState.Error(
                        msg = "No internet and no cache available",
                        type = ErrorType.NETWORK_WITHOUT_CACHE
                    )
                }else {
                    UIState.Loading
                }
            } else {
                UIState.Success(list)
            }
        }.catch { e ->
            emit(
                UIState.Error(
                    msg = "Ups, incorrect input, try again",
                    type = ErrorType.OTHER_WITHOUT_CACHE
                )
            )
        }
    }

    override suspend fun refreshNews(category: String) {
        try {
                if (localDbFetchTime.shouldFetch(category)) {
                    localDbCached.deleteCachedCategory(category)
                    val response = remoteRepository.getNewsByCategory(category, 1)
                    localDbCached.upsetCachedArticle(
                        response.articles.map { it.toArticleDb(category) })
                    localDbFetchTime.db.saveLastCategoryTime(
                        CategoryTime(category, System.currentTimeMillis())
                    )
                }
        } catch (e: Exception) {
    }
}

    override suspend fun searchNews(category: String): Flow<UIState<List<ArticleModel>>> = flow {
        emit(UIState.Loading)
        if (networkHelper.isNetworkAvailable()) {
            try {
                //from api
                val response = remoteRepository.getNewsByCategory(category, 1).articles
                val dataApp = response.map { it.toArticleModel(it.toString()) }
                emit(UIState.Success(dataApp))
            } catch (e: Exception) {
                emit(
                    UIState.Error(
                        "Ups, incorrect input, try again", type = ErrorType.OTHER_WITHOUT_CACHE
                    )
                )
            }
        } else {
            emit(UIState.Error("Check the internet", type = ErrorType.NETWORK_WITHOUT_CACHE))
        }
    }
}

