package com.example.newscompose.domain.manager

import kotlinx.coroutines.flow.Flow

/*
 * Author: Shivang Yadav
 * Created: 6/4/25
 * Description: [Add description here]
 */
interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}