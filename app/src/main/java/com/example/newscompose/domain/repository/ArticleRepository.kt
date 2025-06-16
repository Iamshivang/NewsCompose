package com.example.newscompose.domain.repository

import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.flow.Flow

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */
interface ArticleRepository {

    suspend fun getAllArticles(): Flow<Resource<List<ArticleEntity>>>

    suspend fun insertArticle(article: ArticleEntity): Flow<Resource<Unit>>

    suspend fun deleteArticleById(url: String): Flow<Resource<Unit>>
}