package com.example.newscompose.domain.repository

import com.example.newscompose.data.remote.dto.NewsResponse
import com.example.newscompose.domain.model.Article
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */
interface NewsRepository {

    suspend fun getBreakingNewsArticles(country: String): Flow<Resource<List<Article>>>
    suspend fun getSearchArticles(query: String): Flow<Resource<List<Article>>>
}