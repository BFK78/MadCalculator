package com.example.flytbasecalculator.domain.usecase

import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.AuthenticationRepository
import com.example.flytbasecalculator.domain.model.authentication.Token
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend fun loginUser(email: String, password: String): Response<Token> {
        return authenticationRepository.loginUser(email = email, password = password)
    }

}