package com.example.newscompose.presentation.detail

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newscompose.R
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.model.Source
import com.example.newscompose.utils.getTime
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newscompose.utils.Constants
import com.example.newscompose.utils.Resource
import kotlinx.coroutines.launch
import java.util.*


/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */

@Composable
fun DetailScreen (article: Article,
                  onBackPressed: () -> Boolean,
                  isFromCollectionScreen: Boolean){

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val viewModel: DetailViewModel = hiltViewModel()
    val responseState = viewModel.response
    val scope = rememberCoroutineScope()
    val TAG = "DetailScreen"

    Log.d(TAG, isFromCollectionScreen.toString())

    LaunchedEffect(responseState) {
        when (responseState) {
            is Resource.Success -> {
                Toast.makeText(context, responseState.message ?: "Success", Toast.LENGTH_SHORT).show()
            }
            is Resource.Error -> {
                Log.d("DetailScreen", responseState.message ?: "Error")
            }
            else -> Unit
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        article.urlToImage.let {

            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .crossfade(true)
                        .placeholder(R.drawable.news_placeholder)
                        .build(),
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Crop
                )

                IconsBar(
                    onBrowsingClick = {
                        val uri = Uri.parse(article.url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    },
                    onShareClick = {
                        Intent(Intent.ACTION_SEND).also {
                            it.putExtra(Intent.EXTRA_TEXT, article.url)
                            it.type = "text/plain"
                            if (it.resolveActivity(context.packageManager) != null) {
                                context.startActivity(it)
                            }
                        }
                    },
                    onBackClick = {
                        onBackPressed()
                    },
                    onBookMarkClick = {
                        scope.launch {

                            if(isFromCollectionScreen){
                                viewModel.deleteArticle(article.url)
                                onBackPressed()
                            }else{
                                viewModel.insertArticle(article)
                            }
                        }
                    },
                    flag = isFromCollectionScreen
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "By ${article.author}",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = getTime.getTime(article.publishedAt),
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconsBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit,
    flag: Boolean
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth()
            .statusBarsPadding(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.white),
            navigationIconContentColor = colorResource(id = R.color.white),
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onBookMarkClick) {
                Icon(
                    painter = painterResource(id = if(flag) R.drawable.icon_delete else R.drawable.ic_bookmark),
                    contentDescription = null
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_network),
                    contentDescription = null
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Constants.dummyArticle
}
