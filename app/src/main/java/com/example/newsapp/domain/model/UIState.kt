package com.example.newsapp.domain.model

sealed class UIState<T>(
) {
    class Default<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Error<T>(val msg: String, val type: ErrorType, val data:T? = null) : UIState<T>()
}
enum class ErrorType{
    NETWORK_WITH_CACHE, NETWORK_WITHOUT_CACHE, OTHER_WITH_CACHE, OTHER_WITHOUT_CACHE
}