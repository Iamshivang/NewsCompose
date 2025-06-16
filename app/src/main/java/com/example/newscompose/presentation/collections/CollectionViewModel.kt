package com.example.newscompose.presentation.collections

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.ArticleRepository
import com.example.newscompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val repo: ArticleRepository
): ViewModel(){

    var articles by mutableStateOf<Resource<List<ArticleEntity>>>(Resource.Idle())
        private set

    init {
        getArticles()
    }

    fun getArticles() = viewModelScope.launch{
        repo.getAllArticles().collect {
            articles= it
        }
    }
}