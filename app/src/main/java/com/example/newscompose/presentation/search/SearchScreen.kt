package com.example.newscompose.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newscompose.domain.model.Article
import com.example.newscompose.presentation.common.ErrorView
import com.example.newscompose.presentation.common.LoadingView
import com.example.newscompose.presentation.common.SearchBar
import com.example.newscompose.presentation.common.SuccessView
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */


@Composable
fun SearchScreen(onClick: (Article) -> Unit) {

    val TAG = "SearchScrenn"
    val viewModel: SearchViewModel = hiltViewModel()
    val articlesState = viewModel.articles
    val query = remember { mutableStateOf("") }
    var jober by remember { mutableStateOf<Job?>(null) }

    Scaffold(
        topBar = {
            SearchBar(
                text = query.value,
                readOnly = false,
                onValueChange = {
                    query.value = it
                    jober?.cancel()
                    jober = CoroutineScope(Dispatchers.Main).launch{
                        delay(500)
                        viewModel.search(it)
                    }
                },
                onSearch = {},
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()

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

                SuccessView(
                    articles = articlesState.data!!,
                    padding = innerPadding,
                    modifier = Modifier
                ) {
                    onClick(it)
                }
            }
        }
    }
}