package com.example.newscompose.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newscompose.R
import com.example.newscompose.domain.model.Article
import com.example.newscompose.presentation.common.ErrorView
import com.example.newscompose.presentation.common.LoadingView
import com.example.newscompose.presentation.common.SearchBar
import com.example.newscompose.presentation.common.SuccessView
import com.example.newscompose.utils.Resource

/*
 * Author: Shivang Yadav
 * Created: 6/9/25
 * Description: [Add description here]
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    articlesState: Resource<List<Article>>,
    onClick: (Article) -> Unit,
    toNavigateSearchScreen: () -> Unit,
    toNavigateCollectionScreen: () -> Unit
){
    val TAG = "HomeScreen"

    Scaffold(
        floatingActionButton = {
            fab {
                toNavigateCollectionScreen()
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "app_logo",
                        modifier = Modifier
                            .size(120.dp),
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.Fit
                    )
                },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 14.dp),
            )
        }
    ){  padding ->

        Log.d(TAG, articlesState.data.toString())

        when (articlesState) {
            is Resource.Error -> {
                ErrorView(
                    articlesState.message.toString(), padding
                )
            }

            is Resource.Idle -> {

            }

            is Resource.Loading -> {
                LoadingView(padding)
            }

            is Resource.Success -> {

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(padding)
                ) {

                    SearchBar(
                        text = "",
                        readOnly = true,
                        onValueChange = {},
                        onClick = {
                            toNavigateSearchScreen()
                        },
                        onSearch = {},
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, bottom = 10.dp)
                    )

                    SuccessView(
                        articles = articlesState.data!!,
                        padding = PaddingValues(0.dp),
                        modifier = Modifier
                    ) {
                        onClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun fab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_bookmark),
            contentDescription = "Bookmark"
        )
    }
}
