package com.example.newsapp.domain.model

sealed class UIState<T>(
) {
    class Default<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Error<T>(msg: String) : UIState<T>()
}