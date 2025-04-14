package com.example.newsapp.domain.useCases

import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.UIState
import com.example.newsapp.domain.repository.ResponseCheckRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: ResponseCheckRepository
) {
    operator fun invoke(category: String): Flow<UIState<List<ArticleModel>>> {
        return repository.getNewsByCategory(category)
    }
}