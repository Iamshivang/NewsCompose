package com.example.newscompose.presentation.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newscompose.presentation.collections.CollectionScreen
import com.example.newscompose.presentation.detail.DetailScreen
import com.example.newscompose.presentation.home.HomeScreen
import com.example.newscompose.presentation.home.HomeViewModel
import com.example.newscompose.presentation.navgraph.Routes.HomeScreen
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

    NavHost(
        navController= navController,
        startDestination= if(startDestination == Route.ONBOARDINGSCREEN.route) Routes.OnBoardingScreen else Routes.HomeScreen
    ){

        composable<Routes.OnBoardingScreen>{

            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen{
                viewModel.saveAppEntry()
            }
        }

        composable<Routes.HomeScreen>{

            HomeScreen(
                articlesState,
                onClick ={
                    Log.d("NavGraph", it.toString())
                    viewModel.selectArticle(it)
                    viewModel.notFromCollectionScreen()
                    navController.navigate(Routes.DetailScreen)
                },
                toNavigateSearchScreen = {navController.navigate(Routes.SearchScreen)},
                {
                    navController.navigate(Routes.CollectionScreen)
                }
            )
        }

        composable<Routes.DetailScreen>{

            DetailScreen(viewModel.selectedArticle,
                {navController.popBackStack()},
                viewModel.isFromCollectionScreen)
        }

        composable<Routes.SearchScreen>{
            SearchScreen{
                Log.d("NavGraph", it.toString())
                viewModel.selectArticle(it)
                viewModel.notFromCollectionScreen()
                navController.navigate(Routes.DetailScreen)
            }
        }

        composable<Routes.CollectionScreen>{
            CollectionScreen(
                onClick = {
                Log.d("NavGraph", it.toString())
                viewModel.selectArticle(it)
                    viewModel.fromCollectionScreen()
                navController.navigate(Routes.DetailScreen)
            },
                {
                    navController.popBackStack()
                }
            )
        }
    }
}