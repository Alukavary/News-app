//package com.example.newsapp.presentation.categoryScreen
//
//import android.util.Log
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.newsapp.data.local.preference.DataStoreManager
//import com.example.newsapp.data.models.NewsResponseDto
//import com.example.newsapp.data.remote.api.RetrofitInstance
//import com.example.newsapp.data.repository.SettingsRepositoryImpl
//import com.example.newsapp.domain.model.Article
//import com.example.newsapp.domain.model.mapper.mapNewsApiModelToNews
//import com.example.newsapp.domain.repository.NewsRepositoryRemote
//import com.example.newsapp.domain.repository.SettingsRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class CategoryVM @Inject constructor(
//    private val news: NewsRepositoryRemote,
//    val localDb: SettingsRepository
//) : ViewModel() {
//
//    //    private var _data = MutableLiveData<NewsResponseDto>()
//    private var _data = MutableLiveData<List<Article>>()
//    val data: LiveData<List<Article>> = _data
//
//    var selectedCategory by mutableStateOf("general")
//
//    init {
//        Log.d("MyLod", " мы в инит")
//        viewModelScope.launch {
//            localDb.newsFilter.collect { category ->
//                selectedCategory = category
//
//            }
//
//        }
//    }
//
//    a
//
//}