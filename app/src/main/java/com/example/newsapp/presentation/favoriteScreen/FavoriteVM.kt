package com.example.newsapp.presentation.favoriteScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.models.NewArticle
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.mapper.toDomain
import com.example.newsapp.domain.model.mapper.toEntity
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
val localDb: NewsRepositoryLocal
): ViewModel(){

    private var _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>> = _data

//    init {
//        getArticle()
//        Log.d("MyLog", "ccrjkmrj cnfntq ${data.value}")
//    }

//    fun getArticle() {
//        viewModelScope.launch {
//            val articleFromDb = localDb.getAllArticles()
//            val articles = articleFromDb.map {
//                toDomain(it)
//            }
//            if (articles.isNotEmpty())
//                _data.postValue(articles)
//        }
//    }

    val favoriteArticle: StateFlow<List<Article>> =
    localDb.getAllArticles()
        .map { item -> item.map{toDomain(it)} }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )


    fun deleteArticles(article: Article) {
            viewModelScope.launch {
                localDb.deleteArticle(toEntity(article))
            }
        }

}