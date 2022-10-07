package com.example.flytbasecalculator.domain.repository

import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.model.authentication.Token

interface AuthenticationRepository {

    suspend fun loginUser(email: String, password: String): Response<Token>


}