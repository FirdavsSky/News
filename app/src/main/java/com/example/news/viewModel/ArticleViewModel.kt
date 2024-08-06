package com.example.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.Article
import com.example.news.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
): ViewModel() {

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>> = _article


    fun fetchArticles(
        q: String,
        apiKey: String
    ){
        viewModelScope.launch {
            _article.postValue(repository.fetchArticles(q, apiKey))
        }

    }
}