package com.example.newscompose.presentation.navgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val logoutUseCase: LogoutUseCase
): ViewModel(){

    fun logout() =viewModelScope.launch {
        logoutUseCase.invoke()
    }
}