package com.example.newscompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newscompose.domain.model.Source
import kotlinx.serialization.Serializable

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

@Entity(tableName = "articles")
class ArticleEntity(

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
)