package com.example.newscompose.data.remote

import com.example.newscompose.data.remote.dto.NewsResponse
import com.example.newscompose.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */


interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String,
        @Query("apiKey")
        apiKey: String= Constants.API_KEY,
        @Query("pageSize")
        pageSize: Int= Constants.PAGE_SIZE
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String= Constants.API_KEY
    ):  Response<NewsResponse>
}