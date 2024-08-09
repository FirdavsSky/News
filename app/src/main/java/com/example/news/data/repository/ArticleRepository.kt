package com.example.news.data.repository

import android.util.Log
import com.example.news.data.model.ApiService
import com.example.news.data.model.Article
import javax.inject.Inject


class ArticleRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchArticles(
        q: String,
        apiKey: String
    ): List<Article> {
        val response = apiService.getArticles(q, apiKey)
        if (response.status == "ok") {
            return response.articles
        } else {
            throw Exception("Failed to fetch articles: ${response.status}")

        }
    }
}