package com.example.newsapp.presentation.detailsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.data.models.NewArticle
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.mapper.toEntity
import com.example.newsapp.domain.model.mapper.toDomain
import com.example.newsapp.domain.repository.NewsRepositoryLocal
import com.example.newsapp.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(
    val localDb: NewsRepositoryLocal
) : ViewModel() {

    private var _existing = MutableStateFlow<Boolean>(false)
    val existing: StateFlow<Boolean> =_existing


   fun existingArticle(article: Article) {
       viewModelScope.launch {
           val article = localDb.getAllArticleById(article.title)
              _existing.value = article == null
       }
   }



    fun getDeleteArticles(article: Article) {
        viewModelScope.launch {
            localDb.deleteArticle(toEntity(article))
        }
    }

    fun upsetArticle(article: Article) {
        Log.d("MyLog", "Adding article: $article")

        viewModelScope.launch {
            val existing = localDb.getAllArticleById(article.title)
            if (existing == null)
                localDb.upset(toEntity(article))
            Log.d("MyLog", "from entity: ${existing}")

        }
    }
}
