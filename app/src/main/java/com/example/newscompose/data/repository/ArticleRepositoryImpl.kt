package com.example.newscompose.data.repository

import android.util.Log
import com.example.newscompose.data.local.database.AppDatabase
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.ArticleRepository
import com.example.newscompose.utils.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

class ArticleRepositoryImpl @Inject constructor(
    private val db: AppDatabase
): ArticleRepository{

    val TAG= "ArticleRepositoryImpl"

    override suspend fun getAllArticles(): Flow<Resource<List<ArticleEntity>>> {

        return flow {
            emit(Resource.Loading())
            try {
                val articles = db.articleDao().getAllArticles()
                Log.d(TAG, "Success in fetching article list: $articles")
                emit(Resource.Success(articles))

            } catch (e: Exception) {
                Log.e(TAG, "Error fetching article list: ${e.message}", e)
                emit(Resource.Error(e.message!!))
            }
        }
    }

    override suspend fun insertArticle(article: ArticleEntity): Flow<Resource<Unit>>{

        return flow {
            emit(Resource.Loading())
            try {
                db.articleDao().insertArticle(article)
                Log.d(TAG, "Success in inserting article : $article")
                emit(Resource.Success(Unit))

            } catch (e: Exception) {
                Log.e(TAG, "Error inserting article $article: ${e.message}", e)
                emit(Resource.Error(e.message!!))
            }

        }
    }


    override suspend fun deleteArticleById(url: String): Flow<Resource<Unit>>{

        try {
            db.articleDao().deleteArticleById(url)
        } catch (e: Exception) {
            Log.e("ArticleRepositoryImpl", "Error deleting article: ${e.message}", e)
        }

        return flow {
            emit(Resource.Loading())
            try {
                db.articleDao().deleteArticleById(url)
                Log.d(TAG, "Success in deleting article with url $url")
                emit(Resource.Success(Unit))

            } catch (e: Exception) {
                Log.e(TAG, "Error deleting article with url $url: ${e.message}", e)
                emit(Resource.Error(e.message!!))
            }
        }
    }
}