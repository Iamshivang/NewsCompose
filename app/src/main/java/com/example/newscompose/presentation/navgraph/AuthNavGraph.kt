package com.example.newscompose.presentation.navgraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
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
    navHostController: NavHostController){

    navigation<Route.MainNav>(
        startDestination = Route.OnBoardingScreen
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
    }
}