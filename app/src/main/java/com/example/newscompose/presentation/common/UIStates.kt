package com.example.newscompose.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newscompose.data.local.entity.ArticleEntity
import com.example.newscompose.domain.model.Article

/*
 * Author: Shivang Yadav
 * Created: 6/12/25
 * Description: [Add description here]
 */


@Composable
fun LoadingView(padding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun SuccessView(articles: List<Article>,
                padding: PaddingValues,
                modifier: Modifier,
                onClick: (Article) -> Unit
                ){

    if(articles.isEmpty()){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
            contentAlignment = Alignment.Center) {
            Text(text = "No Articles is Collected"
                , color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium)
        }
    }else{
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            items(items = articles){ article ->
                ArticleCard(modifier, article, onClick= onClick)
            }
        }
    }
}

@Composable
fun ErrorView(message: String, padding: PaddingValues) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp),
        contentAlignment = Alignment.Center){
        Text(text = "Error: $message"
            ,color = Color.Red,
            style = MaterialTheme.typography.titleLarge)
    }
}