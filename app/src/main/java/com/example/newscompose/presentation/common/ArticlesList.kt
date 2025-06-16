package com.example.newscompose.presentation.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newscompose.domain.model.Article

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */


@Composable
fun ShowArticles(
    articles: List<Article>,
    onClick: (Article) -> Unit
){
    LazyColumn {
        items(articles.size) { indx ->
            ArticleCard(
                modifier = Modifier,
                article = articles[indx],
                onClick = onClick)
        }
    }
}