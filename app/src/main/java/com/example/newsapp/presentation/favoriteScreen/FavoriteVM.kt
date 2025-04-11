package com.example.newsapp.presentation.favoriteScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.domain.model.ArticleModel
import com.example.newsapp.domain.model.mapper.toArticleModel
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    localDb: NewsRepositoryLocal
) : ViewModel() {
    val localdb = localDb

    private var _data = MutableLiveData<List<ArticleModel>>()
    val data: LiveData<List<ArticleModel>> = _data

    val favoriteArticle: StateFlow<List<ArticleModel>> =
        localDb.getArticlesIsFavoriteFromDb()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )
    fun allFavDelete(
        localDb: NewsRepositoryLocal
    ) {
        viewModelScope.launch {
            localDb.deleteIsFavCategory(isFavorite = true)
        }
    }
}