package com.example.newscompose.presentation.collections

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newscompose.R
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.model.Source
import com.example.newscompose.presentation.common.ErrorView
import com.example.newscompose.presentation.common.LoadingView
import com.example.newscompose.presentation.common.SuccessView
import com.example.newscompose.utils.Resource

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    onClick: (Article) -> Unit,
    onBackClick: () -> Unit
) {

    val TAG = "CollectionScreen"
    val viewModel: CollectionViewModel = hiltViewModel()
    val articlesState = viewModel.articles

    LaunchedEffect(key1= true) {
        viewModel.getArticles()
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Collections",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Log.d(TAG, articlesState.data.toString())

        when (articlesState) {
            is Resource.Error -> {
                ErrorView(
                    articlesState.message.toString(), innerPadding
                )
            }

            is Resource.Idle -> {

            }

            is Resource.Loading -> {
                LoadingView(innerPadding)
            }

            is Resource.Success -> {

                val result = articlesState.data?.map {
                    Article(
                        author = it.author,
                        content = it.description,
                        description = it.content,
                        publishedAt = it.publishedAt,
                        source = Source("", it.source),
                        title = it.title,
                        url = it.url,
                        urlToImage = it.urlToImage
                    )
                }

                SuccessView(
                    articles = result!!,
                    padding = innerPadding,
                    modifier = Modifier
                ) {
                    onClick(it)
                }
            }
        }
    }
}