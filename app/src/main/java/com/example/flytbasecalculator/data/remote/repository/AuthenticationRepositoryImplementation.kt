package com.example.flytbasecalculator.data.remote.repository

import com.example.flytbasecalculator.data.remote.api.AuthenticationApi
import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.AuthenticationRepository
import com.example.flytbasecalculator.domain.model.authentication.Token
import java.lang.Exception
import javax.inject.Inject

class AuthenticationRepositoryImplementation @Inject constructor(
    private val api: AuthenticationApi,
): AuthenticationRepository {

    override suspend fun loginUser(email: String, password: String): Response<Token> {
        return try {
            val response = api.loginUser(email = email, password = password).toResponseToken()
            response
        } catch (e: Exception) {
            e.printStackTrace()
            Response(status = false, message = e.message.toString())
        }
    }

}