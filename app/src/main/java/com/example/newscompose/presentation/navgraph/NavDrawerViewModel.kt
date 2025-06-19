package com.example.newscompose.presentation.navgraph

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.auth.GetUserNameUseCase
import com.example.newscompose.domain.usecases.auth.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */

@HiltViewModel
class NavDrawerViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
): ViewModel(){

    var userName by mutableStateOf<String>("User")
        private set

    init {
        viewModelScope.launch {
            getUserNameUseCase.invoke().collect {
                userName = it
            }
        }
    }

    fun logout() =viewModelScope.launch {
        logoutUseCase.invoke()
    }
}