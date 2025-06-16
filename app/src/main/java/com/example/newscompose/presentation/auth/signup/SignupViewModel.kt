package com.example.newscompose.presentation.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.auth.SignupUseCase
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
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
): ViewModel(){

    var signup by mutableStateOf<Resource<AuthResult>>(Resource.Idle())
        private set

    fun signup(name: String, email: String, password: String)= viewModelScope.launch {
        signupUseCase.invoke(name, email, password).collect { response ->
            signup = response
        }
    }
}