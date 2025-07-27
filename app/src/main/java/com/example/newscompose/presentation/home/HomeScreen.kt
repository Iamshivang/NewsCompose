package com.example.newscompose.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newscompose.R
import com.example.newscompose.domain.model.Article
import com.example.newscompose.presentation.common.ErrorView
import com.example.newscompose.presentation.common.LoadingView
import com.example.newscompose.presentation.common.SearchBar
import com.example.newscompose.presentation.common.SuccessView
import com.example.newscompose.presentation.navgraph.MainNavGraph
import com.example.newscompose.presentation.navgraph.Route
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.launch

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
    navHostController: NavHostController,
    drawerState: DrawerState
) {
    val TAG = "HomeScreen"
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            fab {
                navHostController.navigate(Route.CollectionScreen)
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "app_logo",
                        modifier = Modifier
                            .size(65.dp)
                            .padding(bottom = 8.dp),
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.Fit
                    )
                },
                navigationIcon = {
                    if(drawerState != null) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = ""
                            , tint = MaterialTheme.colorScheme.primary)
                        }
                    }
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
                            navHostController.navigate(Route.SearchScreen)
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
