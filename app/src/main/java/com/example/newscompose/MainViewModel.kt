package com.example.newscompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.AppEntryUseCases
import com.example.newscompose.domain.usecases.auth.IsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/8/25
 * Description: [Add description here]
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val isLoggedInUseCase: IsLoggedInUseCase
): ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<String>("onBoardingScreen")
        private set

    init {
        decideStartDestination()
    }

    private fun decideStartDestination()= viewModelScope.launch {

        appEntryUseCases.readAppEntry.invoke().collect { isOnBoardingCompleted ->

            if(!isOnBoardingCompleted){
                startDestination = "onBoardingScreen"
            }else{

                isLoggedInUseCase.invoke().collect { isUserLoggedIn ->

                    if(isUserLoggedIn){
                        startDestination = "homeScreen"
                    }else{
                        startDestination = "loginScreen"
                    }
                }
            }
            delay(300)
            splashCondition = false
        }
    }
}