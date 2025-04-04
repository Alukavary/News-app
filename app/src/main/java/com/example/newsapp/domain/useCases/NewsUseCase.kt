package com.example.newsapp.domain.useCase

import android.util.Log
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.model.mapper.toArticleDb
import com.example.newsapp.domain.model.mapper.toArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.repository.NewsRepositoryRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.collections.map

class NewsUseCase @Inject constructor(
    val localDbCached: NewsRepositoryLocal,
    val remoteRepository: NewsRepositoryRemote
) {
    operator fun invoke(
        category: String,
    ): Flow<UIState<List<ArticleModel>>> =
        flow {
            emit(UIState.Loading())
            try {
                //from api
                val response = remoteRepository.getNewsByCategory(category, 1)
                Log.d("MyLog", "response ${response.articles.size}")

                //to db
                localDbCached.upsetCachedArticle(response.articles.map {
                    it.toArticleDb(
                        category
                    )
                })
                //from db
                val localData = localDbCached.getArticlesByCategory(category).first()
                val dataForApp = localData.map {it.toArticleModel()}
                Log.d("MyLog", "localData ${localData.size}")

                emit(UIState.Success<List<ArticleModel>>(dataForApp))
            } catch (e: Exception) {
                Log.d("MyLog", "error ${e.message}")
                emit(UIState.Error())
            }
        }
}

