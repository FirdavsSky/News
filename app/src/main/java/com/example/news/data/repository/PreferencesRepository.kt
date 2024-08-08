package com.example.news.data.repository

import android.content.SharedPreferences

interface PreferencesRepository {

    suspend fun getEnablesCategories(): List<String>

    fun getBooleanPreference(key: String, defaultValue: Boolean): Boolean

    fun setBooleanPreference(key: String, value: Boolean)

    class Base(private val sharedPreferences: SharedPreferences) : PreferencesRepository {

        private val keys = listOf("Politics", "Sport", "Nature", "Science")

        override suspend fun getEnablesCategories(): List<String> = keys.associateWith { key ->
            getBooleanPreference(key, true)
        }.filter { (_, value) ->
            value
        }.map { (key, _) ->
            key
        }

        override fun getBooleanPreference(key: String, defaultValue: Boolean): Boolean {
            return sharedPreferences.getBoolean(key, defaultValue)
        }

        override fun setBooleanPreference(key: String, value: Boolean) {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }
    }
}