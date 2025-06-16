package com.example.newscompose.domain.repository

import com.example.newscompose.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

/*
 * Author: Shivang Yadav
 * Created: 6/16/25
 * Description: [Add description here]
 */

interface AuthRepository {

    suspend fun login(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun signup(name: String, email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun logout()
    suspend fun userUid(): String
    suspend fun isUserLoggedIn(): Boolean
    suspend fun resetPassword(email: String): Flow<Resource<Void?>>
}