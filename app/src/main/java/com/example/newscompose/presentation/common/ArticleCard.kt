package com.example.newscompose.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import com.example.newscompose.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.model.Source
import com.example.newscompose.presentation.theme.NewsComposeTheme
import com.example.newscompose.utils.getTime

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onClick(article)
                       },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .placeholder(R.drawable.news_placeholder)
                    .build(),
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Start color
                                Color.Black  // End color
                            ),
                        ),
                        alpha = 1.0f,
                    )
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontWeight = FontWeight.Bold),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Icon(
                            painter = painterResource(R.drawable.ic_search_document),
                            contentDescription = "Time Icon",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                                .padding(end = 4.dp)
                        )

                        Text(
                            text = article.source.name,
                            style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Icon(
                            painter = painterResource(R.drawable.ic_time),
                            contentDescription = "Time Icon",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                                .padding(end = 4.dp)
                        )

                        Text(
                            text = getTime.getTime(article.publishedAt),
                            style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun ArticleCardPreview(){

    NewsComposeTheme {

        ArticleCard(
            Modifier,
            Article(
                author = "John Doe",
                content = "This is the content of the article.",
                description = "A brief description of the article.",
                publishedAt = "2025-06-09T15:00:00Z",
                source = Source(id = "abc123", name = "NewsSource"),
                title = "Breaking News: Kotlin is Awesome!",
                url = "https://www.example.com/article/12345",
                urlToImage = "https://www.example.com/images/article12345.jpg"
            ),
            onClick = {}
        )

    }
}