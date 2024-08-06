package com.example.news.data.model

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getArticles(
        @Query("q") fromDate: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}