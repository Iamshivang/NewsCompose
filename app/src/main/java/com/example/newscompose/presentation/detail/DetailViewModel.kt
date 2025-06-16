package com.example.newscompose.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.ArticleRepository
import com.example.newscompose.utils.Constants
import com.example.newscompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: ArticleRepository
): ViewModel(){

    var response by mutableStateOf<Resource<Unit>>(Resource.Idle())
        private set

    fun insertArticle(article: Article)= viewModelScope.launch {

        val articleEntity = ArticleEntity(
            author = article.author?: Constants.dummyArticle.author,
            content = article.content?: Constants.dummyArticle.content,
            description = article.description?: Constants.dummyArticle.description,
            publishedAt = article.publishedAt?: Constants.dummyArticle.publishedAt,
            source = article.source.name?: Constants.dummyArticle.source.name,
            title = article.title?: Constants.dummyArticle.title,
            url = article.url?: Constants.dummyArticle.url,
            urlToImage = article.urlToImage?: Constants.dummyArticle.urlToImage
        )

        repo.insertArticle(articleEntity).collect {
            response = it
        }
        response = Resource.Idle()
    }

    fun deleteArticle(url: String)= viewModelScope.launch {

       repo.deleteArticleById(url).collect {
           response = it
       }
        response = Resource.Idle()
    }

}