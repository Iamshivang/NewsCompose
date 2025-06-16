package com.example.newscompose.presentation.navgraph

import com.example.newscompose.domain.model.Article
import kotlinx.serialization.Serializable

/*
 * Author: Shivang Yadav
 * Created: 6/8/25
 * Description: [Add description here]
 */
enum class Route(
    val route: String
) {

    ONBOARDINGSCREEN(route = "OnBoardingScreen"),
    HOMESCREEN(route = "homeScreen"),
    SEARCHSCREEN(route = "searchScreen"),
    BOOKMARKSCREEN(route = "bookmarksScreen"),
    DETAILSCREEN(route = "detailScreen"),
    APPSTARTNAVIGATION(route = "appStartNavigation"),
    NEWSNAVIGATION(route = "newsNavigation"),
    NEWSNAVIGATORSCREEN(route = "newsNavigatorScreen")
}

sealed class Routes{

    @Serializable
    data object HomeScreen: Routes()

    @Serializable
    data object OnBoardingScreen: Routes()

    @Serializable
    data object SearchScreen: Routes()

    @Serializable
    data object CollectionScreen: Routes()

    @Serializable
    data object DetailScreen: Routes()

}