package com.example.newscompose.presentation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newscompose.R

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */
data class DrawerMenu(
    val icon: Int,
    val title: String,
    val route: Route
)



val menus = arrayOf(
    DrawerMenu(R.drawable.ic_home, "Home", Route.HomeScreen),
    DrawerMenu(R.drawable.ic_search, "Search", Route.SearchScreen),
    DrawerMenu(R.drawable.ic_bookmark, "Collection", Route.CollectionScreen)
)
