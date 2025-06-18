package com.example.newscompose.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newscompose.presentation.home.HomeScreen

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    startScreen: String){

    NavHost(
        navController = navHostController,
        startDestination = Route.AuthNav
    ){

        authNavGraph(navHostController, startScreen)

    }
}