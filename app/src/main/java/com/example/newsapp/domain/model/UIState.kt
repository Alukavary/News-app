package com.example.newsapp.domain.model

sealed class UIState<out T>(
) {
    data object Default : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val msg: String, val type: ErrorType, val data:T? = null) : UIState<T>()
}
enum class ErrorType{
    NETWORK_WITH_CACHE, NETWORK_WITHOUT_CACHE, OTHER_WITH_CACHE, OTHER_WITHOUT_CACHE
}

//
//sealed interface UIStatePart{
//    data object Default : UIStatePart
//    data object Loading : UIStatePart
//    data class Success(val data: List<ArticleModel>) : UIStatePart
//
//    data class Error(val msg: String, val type: ErrorType, val data: List<ArticleModel> = emptyList()) : UIStatePart
//}



