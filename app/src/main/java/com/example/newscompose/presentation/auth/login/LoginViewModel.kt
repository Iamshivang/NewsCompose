package com.example.newscompose.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.auth.LoginUseCase
import com.example.newscompose.domain.usecases.auth.ResetPasswordUseCase
import com.example.newscompose.utils.Resource
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/16/25
 * Description: [Add description here]
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
): ViewModel(){

    var login by mutableStateOf<Resource<AuthResult>>(Resource.Idle())
        private set

    var resetPassword by mutableStateOf<Resource<Void?>>(Resource.Idle())
        private set

    fun login(email: String, password: String)= viewModelScope.launch {
        loginUseCase.invoke(email, password).collect { response ->
            login = response
        }
    }

    fun resetPassword(email: String) = viewModelScope.launch {
        resetPasswordUseCase.invoke(email).collect { response ->
            resetPassword = response
        }
    }
}