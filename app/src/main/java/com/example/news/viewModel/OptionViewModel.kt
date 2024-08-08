package com.example.news.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _switchStates = MutableLiveData<Map<String, Boolean>>()
    val switchStates: LiveData<Map<String, Boolean>> get() = _switchStates

    init {
        loadPreferences()
    }

    private fun loadPreferences() {
        val keys = listOf("Politics", "Sport", "Nature", "Science")
        val states = keys.associateWith { key ->
            preferencesRepository.getBooleanPreference(key, true)
        }
        _switchStates.value = states
    }

    fun updatePreference(key: String, value: Boolean) {
        preferencesRepository.setBooleanPreference(key, value)
        loadPreferences()
    }
}