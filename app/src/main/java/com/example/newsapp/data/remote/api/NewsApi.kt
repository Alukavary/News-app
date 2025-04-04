package com.example.newsapp.data.remote.api

import com.example.newsapp.data.models.api.NewsResponseDto
import com.example.newsapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")

    suspend fun getHeadlines(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apikey: String = API_KEY

    ): NewsResponseDto


    @GET("v2/everything")

    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apikey: String = API_KEY

    ): NewsResponseDto


}