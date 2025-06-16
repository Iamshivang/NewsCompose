package com.example.newscompose.data.repository

import android.util.Log
import com.example.newscompose.domain.repository.AuthRepository
import com.example.newscompose.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Executor
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/16/25
 * Description: [Add description here]
 */


class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository{

    private val TAG = "AuthRepositoryImpl"

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {

        return flow {
            emit(Resource.Loading())
            try {
                val data = auth.signInWithEmailAndPassword(email, password).await()
                Log.d(TAG, "Success in login $data")
                emit(Resource.Success(data))
            }catch (e: Exception){
                Log.e(TAG, "Error in login $e")
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {

        return flow {
            emit(Resource.Loading())
            try {
                val data = auth.createUserWithEmailAndPassword(email, password).await()
                data.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
                Log.d(TAG, "Success in signup $data")
                emit(Resource.Success(data))
            }catch (e: Exception){
                Log.e(TAG, "Error in signup $e")
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override suspend fun logout() {

        try{
            auth.signOut()
            Log.d(TAG, "Success in User singnedOut")
        }catch (e: Exception){
            Log.e(TAG, "Error in logout $e")
        }
    }

    override suspend fun userUid(): String {

        try {
            Log.d(TAG, "Success in userUid ${auth.currentUser?.uid}")
            return auth.currentUser?.uid.toString()
        }catch (e: Exception){
            Log.e(TAG, "Error in userUid $e")
        }

        return ""
    }

    override suspend fun isUserLoggedIn(): Boolean {

        try {
            Log.d(TAG, "Success in isUserLoggedIn ${auth.currentUser != null}")
            return auth.currentUser != null
        }catch (e: Exception){
            Log.e(TAG, "Error in isUserLoggedIn $e")
        }
        return false
    }

    override suspend fun resetPassword(email: String): Flow<Resource<Void?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = auth.sendPasswordResetEmail(email).await()
                Log.d(TAG, "Success in resetPassword $data")
                emit(Resource.Success(data))
            }catch (e: Exception){
                Log.e(TAG, "Error in login $e")
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}