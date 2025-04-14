package com.example.newsapp.presentation.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.useCases.ExtractArticleTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(
    val localDb: NewsRepositoryLocal,
    val extractArticle : ExtractArticleTextUseCase
) : ViewModel() {

    private var _article = MutableStateFlow<String>("Loading...")
    val article = _article.asStateFlow()

    fun loadingArticleText(url: String){
        viewModelScope.launch {
            _article.value = extractArticle(url)
        }
    }

    fun resetIsFavoriteOrNot(article: ArticleModel, isFavorite: Boolean) {
        viewModelScope.launch {
            localDb.resetFavoriteFalse(isFavorite, article.title)
        }
    }
}

