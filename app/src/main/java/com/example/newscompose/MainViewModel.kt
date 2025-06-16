package com.example.newscompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.AppEntryUseCases
import com.example.newscompose.presentation.navgraph.Route
import com.example.newscompose.presentation.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/8/25
 * Description: [Add description here]
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.ONBOARDINGSCREEN.route)
        private set


    init {

        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->

            if(shouldStartFromHomeScreen){
                 startDestination = Route.HOMESCREEN.route
            }else{
                startDestination = Route.ONBOARDINGSCREEN.route
            }

            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)
    }
}