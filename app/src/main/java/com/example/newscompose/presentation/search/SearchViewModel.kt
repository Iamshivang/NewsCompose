package com.example.newscompose.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.model.Source
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: NewsRepository
): ViewModel(){

    var articles by mutableStateOf<Resource<List<Article>>>(Resource.Idle())
        private set

    fun search(q: String){

        viewModelScope.launch(Dispatchers.IO) {
            repo.getSearchArticles(q).collectLatest {
                articles = it
            }
        }
    }
}