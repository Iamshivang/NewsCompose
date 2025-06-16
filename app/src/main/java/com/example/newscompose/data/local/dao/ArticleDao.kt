package com.example.newscompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteArticleById(url: String)
}