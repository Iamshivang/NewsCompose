package com.example.newscompose.presentation.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newscompose.presentation.auth.login.LoginScreen
import com.example.newscompose.presentation.auth.signup.SignupScreen
import com.example.newscompose.presentation.collections.CollectionScreen
import com.example.newscompose.presentation.detail.DetailScreen
import com.example.newscompose.presentation.home.HomeScreen
import com.example.newscompose.presentation.home.HomeViewModel
import com.example.newscompose.presentation.onboarding.OnBoardingScreen
import com.example.newscompose.presentation.onboarding.OnBoardingViewModel
import com.example.newscompose.presentation.search.SearchScreen

/*
 * Author: Shivang Yadav
 * Created: 6/8/25
 * Description: [Add description here]
 */


@Composable
fun NavGraph(
    startDestination: String
){

    val navController = rememberNavController()
    val viewModel: HomeViewModel = hiltViewModel()
    val articlesState = viewModel.articles

    val startScreen: Route = when(startDestination){

        "onBoardingScreen" -> Route.OnBoardingScreen
        "loginScreen" -> Route.LoginScreen
        else -> Route.HomeScreen
    }

    NavHost(
        navController= navController,
        startDestination= startScreen
    ){

        composable<Route.OnBoardingScreen>{

            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen{
                viewModel.saveAppEntry()
            }
        }

        composable<Route.LoginScreen> {

            LoginScreen(navController)
        }

        composable<Route.SignupScreen>{

            SignupScreen(
                navController
            )
        }

        composable<Route.HomeScreen>{

            HomeScreen(
                articlesState,
                onClick ={
                    Log.d("NavGraph", it.toString())
                    viewModel.selectArticle(it)
                    viewModel.notFromCollectionScreen()
                    navController.navigate(Route.DetailScreen)
                },
                toNavigateSearchScreen = {navController.navigate(Route.SearchScreen)},
                {
                    navController.navigate(Route.CollectionScreen)
                }
            )
        }

        composable<Route.DetailScreen>{

            DetailScreen(viewModel.selectedArticle,
                {navController.popBackStack()},
                viewModel.isFromCollectionScreen)
        }

        composable<Route.SearchScreen>{
            SearchScreen{
                Log.d("NavGraph", it.toString())
                viewModel.selectArticle(it)
                viewModel.notFromCollectionScreen()
                navController.navigate(Route.DetailScreen)
            }
        }

        composable<Route.CollectionScreen>{
            CollectionScreen(
                onClick = {
                Log.d("NavGraph", it.toString())
                viewModel.selectArticle(it)
                    viewModel.fromCollectionScreen()
                navController.navigate(Route.DetailScreen)
            },
                {
                    navController.popBackStack()
                }
            )
        }
    }
}