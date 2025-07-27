package com.example.newscompose.utils

import com.example.newscompose.BuildConfig
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.model.Source

/*
 * Author: Shivang Yadav
 * Created: 6/4/25
 * Description: [Add description here]
 */
object Constants {

    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY = "appEntry"
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL= "https://newsapi.org/"
    const val PAGE_SIZE = 50
    val dummyArticle = Article(
        author = "John Doe",
        content = "This is the content of the article.",
        description = "A brief description of the article.",
        publishedAt = "2025-06-10T03:51:45Z",
        source = Source(id = "1", name = "Sample News"),
        title = "Sample Article Title",
        url = "https://www.example.com",
        urlToImage = "https://www.example.com/image.jpg"
    )
}