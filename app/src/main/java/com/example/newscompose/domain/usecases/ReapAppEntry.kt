package com.example.newscompose.domain.usecases

import com.example.newscompose.domain.manager.LocalUserManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

/*
 * Author: Shivang Yadav
 * Created: 6/4/25
 * Description: [Add description here]
 */
class ReapAppEntry @Inject constructor(
    val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}