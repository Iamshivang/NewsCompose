package com.example.newscompose.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newscompose.R
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onClick: (Article) -> Unit, onBackClick: () -> Unit) {

    val TAG = "SearchScrenn"
    val viewModel: SearchViewModel = hiltViewModel()
    val articlesState = viewModel.articles
    val query = remember { mutableStateOf("") }
    var jober by remember { mutableStateOf<Job?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
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