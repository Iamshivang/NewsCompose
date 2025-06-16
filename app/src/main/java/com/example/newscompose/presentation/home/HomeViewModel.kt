package com.example.newscompose.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Source
import com.example.newscompose.utils.Constants
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: NewsRepository
): ViewModel(){
    
    val TAG = "HomeViewModel"

    var articles by mutableStateOf<Resource<List<Article>>>(Resource.Idle())
        private set

    init {
        getArticles()
    }
    
    fun getArticles(country: String= "us")= viewModelScope.launch(Dispatchers.IO) {

        repo.getBreakingNewsArticles(country).collect { 
            articles = it
        }
    }

    var selectedArticle: Article by mutableStateOf(Constants.dummyArticle)
        private set


    fun selectArticle(article: Article) {
        selectedArticle = article
        Log.d(TAG, selectedArticle.toString())
    }


    var isFromCollectionScreen by mutableStateOf(false)
        private set

    fun fromCollectionScreen(){
        isFromCollectionScreen= true
    }
    fun notFromCollectionScreen(){
        isFromCollectionScreen= false
    }
}