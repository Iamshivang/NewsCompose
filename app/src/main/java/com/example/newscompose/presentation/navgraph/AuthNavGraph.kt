package com.example.newscompose.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newscompose.presentation.auth.login.LoginScreen
import com.example.newscompose.presentation.auth.signup.SignupScreen
import com.example.newscompose.presentation.onboarding.OnBoardingScreen
import com.example.newscompose.presentation.onboarding.OnBoardingViewModel

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    startAuth: String){

    val startRoute: Route = if(startAuth == "loginScreen") Route.LoginScreen else Route.OnBoardingScreen

    navigation<Route.AuthNav>(
        startDestination = Route.AuthNav
    ) {

        composable<Route.OnBoardingScreen>{

            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen{
                viewModel.saveAppEntry()
            }
        }

        composable<Route.LoginScreen> {

            LoginScreen(
                navController
            )
        }

        composable<Route.SignupScreen>{

            SignupScreen(
                navController
            )
        }
    }
}