package com.example.newscompose.data.repository

import android.util.Log
import com.example.newscompose.data.remote.NewsApi
import com.example.newscompose.data.remote.dto.NewsResponse
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
): NewsRepository {

    val TAG = "NewsRepositoryImpl"

    override suspend fun getBreakingNewsArticles(country: String): Flow<Resource<List<Article>>>{

        return flow {
            emit(Resource.Loading())
            try {
                val result = api.getBreakingNews(country)

                if(result.isSuccessful){
                    emit(Resource.Success(result.body()!!.articles))
                    Log.d(TAG, result.message())
                }else{
                    emit(Resource.Error(result.message()))
                    Log.e(TAG, "Error: ${result.message()}")
                }
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString()))
                Log.e(TAG, "Error: ${e.message.toString()}")
            }
        }
    }

    override suspend fun getSearchArticles(query: String): Flow<Resource<List<Article>>>{

        return flow {
            emit(Resource.Loading())
            try {
                val result = api.searchForNews(query)

                if(result.isSuccessful){
                    emit(Resource.Success(result.body()!!.articles))
                    Log.d(TAG, result.message())
                }else{
                    emit(Resource.Error(result.message()))
                    Log.e(TAG, "Error: ${result.message()}")
                }
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString()))
                Log.e(TAG, "Error: ${e.message.toString()}")
            }
        }
    }
}