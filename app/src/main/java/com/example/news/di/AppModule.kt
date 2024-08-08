package com.example.news.di

import android.content.Context
import android.content.SharedPreferences
import com.example.news.R
import com.example.news.data.model.ApiService
import com.example.news.data.repository.ArticleRepository
import com.example.news.data.repository.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val API_KEY = "24d75cfb90ff4e22ae6cb210ec8a485d"

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideArticleRepository(apiService: ApiService): ArticleRepository {
        return ArticleRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(sharedPreferences: SharedPreferences): PreferencesRepository {
        return PreferencesRepository.Base(sharedPreferences)
    }

}