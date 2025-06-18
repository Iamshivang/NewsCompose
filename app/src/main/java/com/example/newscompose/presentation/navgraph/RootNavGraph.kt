package com.example.newscompose.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newscompose.presentation.auth.login.LoginScreen
import com.example.newscompose.presentation.auth.signup.SignupScreen
import com.example.newscompose.presentation.onboarding.OnBoardingScreen
import com.example.newscompose.presentation.onboarding.OnBoardingViewModel

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */

@Composable
fun RootNavGraph(
    startScreen: String){

    val navHostController = rememberNavController()
    val startRoute: Route = when(startScreen){
        "loginScreen" -> Route.LoginScreen
        "homeScreen" -> Route.HomeScreen
        else -> Route.OnBoardingScreen
    }

    NavHost(
        navController = navHostController,
        startDestination = startRoute
    ){

        composable<Route.OnBoardingScreen>{

            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen{
                viewModel.saveAppEntry()
            }
        }

        composable<Route.LoginScreen> {

            LoginScreen(
                navHostController
            )
        }

        composable<Route.SignupScreen>{

            SignupScreen(
                navHostController
            )
        }

        mainNavGraph(navHostController)

    }
}