package com.example.newsapp.presentation.detailsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(
    val localDb: NewsRepositoryLocal
) : ViewModel() {

    fun resetIsFavoriteOrNot(article: ArticleModel, isFavorite: Boolean) {
        viewModelScope.launch {
            localDb.resetFavoriteFalse( isFavorite, article.title)
        }
    }
}

