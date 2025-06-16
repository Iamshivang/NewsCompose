package com.example.newscompose.domain.usecases.auth

import com.example.newscompose.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/16/25
 * Description: [Add description here]
 */
class GetUserUidUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() =
        flow{ emit(authRepository.userUid())}
}