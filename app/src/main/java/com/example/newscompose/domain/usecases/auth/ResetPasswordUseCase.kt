package com.example.newscompose.domain.usecases.auth

import com.example.newscompose.domain.repository.AuthRepository
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/16/25
 * Description: [Add description here]
 */
class ResetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String) =
        authRepository.resetPassword(email)
}