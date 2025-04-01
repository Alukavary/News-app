package com.example.newsapp.domain.model

sealed class State<T>(
    val data: T? = null,
    val type: TypeError? = null,
    val message: String? = null
) {

    class Loading<T> : State<T>()
    class Success<T>(data: T): State<T>(data)
    class Error<T>(type: TypeError, message: String): State<T>(type = type, message = message)
}


enum class TypeError{
    NETWORK, OTHER
}
