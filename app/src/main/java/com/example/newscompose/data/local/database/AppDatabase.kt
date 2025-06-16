package com.example.newscompose.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newscompose.data.local.dao.ArticleDao
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article
import kotlin.jvm.java

/*
 * Author: Shivang Yadav
 * Created: 5/15/25
 * Description: [Add description here]
 */

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object{

        fun getInstance(context: Context): AppDatabase{

            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Article.db")
                .allowMainThreadQueries()
                .build()
        }
    }
}