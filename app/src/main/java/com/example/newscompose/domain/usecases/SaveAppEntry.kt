package com.example.newscompose.domain.usecases

import com.example.newscompose.domain.manager.LocalUserManager
import jakarta.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/4/25
 * Description: [Add description here]
 */
class SaveAppEntry @Inject constructor(
    val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}