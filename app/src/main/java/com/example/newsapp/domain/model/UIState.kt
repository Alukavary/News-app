package com.example.newsapp.domain.model

sealed class UIState<out T>() {
    data object Empty : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val msg: String, val type: ErrorType, val data: T? = null) : UIState<T>()
}

enum class ErrorType {

    NETWORK_WITH_CACHE,
    NETWORK_WITHOUT_CACHE,
    OTHER_WITHOUT_CACHE
}

