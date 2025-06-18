package com.example.newscompose.presentation.navgraph

import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newscompose.presentation.collections.CollectionScreen
import com.example.newscompose.presentation.detail.DetailScreen
import com.example.newscompose.presentation.home.HomeScreen
import com.example.newscompose.presentation.home.HomeViewModel
import com.example.newscompose.presentation.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */


@Composable
fun MainNavGraph(
    startScreen: String){

    val navController = rememberNavController()
    val viewModel: HomeViewModel = hiltViewModel()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val articlesState = viewModel.articles

    val startRoute: Route = when(startScreen){
        "loginScreen" -> Route.LoginScreen
        "homeScreen" -> Route.HomeScreen
        else -> Route.OnBoardingScreen
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    menus = menus,
                    onMenuClick = { route ->
                        coroutineScope.launch {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route)
                        }
                    },
                    navHostController = navController,
                    drawerState = drawerState
                )
            }
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = startRoute
        ){

            composable<Route.HomeScreen>{

                HomeScreen(
                    articlesState,
                    onClick ={
                        Log.d("NavGraph", it.toString())
                        viewModel.selectArticle(it)
                        viewModel.notFromCollectionScreen()
                        navController.navigate(Route.DetailScreen)
                    },
                    navController,
                    drawerState
                )
            }

            composable<Route.DetailScreen>{

                DetailScreen(viewModel.selectedArticle,
                    {navController.popBackStack()},
                    viewModel.isFromCollectionScreen)
            }

            composable<Route.SearchScreen>{
                SearchScreen(
                    {
                        Log.d("NavGraph", it.toString())
                        viewModel.selectArticle(it)
                        viewModel.notFromCollectionScreen()
                        navController.navigate(Route.DetailScreen)
                    },
                    {navController.popBackStack()}
                )
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

            authNavGraph(navController)
        }
    }
}