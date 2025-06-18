package com.example.newscompose.presentation.navgraph

import kotlinx.serialization.Serializable

/*
 * Author: Shivang Yadav
 * Created: 6/8/25
 * Description: [Add description here]
 */

sealed class Route{

    // Graph
    @Serializable
    data object MainNav: Route()

    // Screens

    @Serializable
    data object LoginScreen: Route()

    @Serializable
    data object SignupScreen: Route()

    @Serializable
    data object HomeScreen: Route()

    @Serializable
    data object OnBoardingScreen: Route()

    @Serializable
    data object SearchScreen: Route()

    @Serializable
    data object CollectionScreen: Route()

    @Serializable
    data object DetailScreen: Route()

}