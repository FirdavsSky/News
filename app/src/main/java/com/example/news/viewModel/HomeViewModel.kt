package com.example.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel(){

    private val _switchStates = MutableLiveData<List<String>>()
    val switchStates: LiveData<List<String>> get() = _switchStates

    init {

        viewModelScope.launch(Dispatchers.IO) {

            loadPreferences()
        }
    }

    private suspend fun loadPreferences(){

        val categories = preferencesRepository.getEnablesCategories()
        _switchStates.postValue(categories)
    }
}