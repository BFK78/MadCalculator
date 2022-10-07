package com.example.flytbasecalculator.data.remote.dto

import com.example.flytbasecalculator.domain.model.authentication.Token
import com.example.flytbasecalculator.domain.model.authentication.User

data class AuthDataDto(
    val token: String,
    val user: User
) {
    fun toToken(): Token {
        return Token(token = token, user = user)
    }
}
