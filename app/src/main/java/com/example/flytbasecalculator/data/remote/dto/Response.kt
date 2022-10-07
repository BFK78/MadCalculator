package com.example.flytbasecalculator.data.remote.dto

import com.example.flytbasecalculator.domain.model.authentication.Token

data class Response<T>(
    val status: Boolean,
    val message: String,
    val data: T? = null
){
    fun toResponseToken(): Response<Token> {
        return if (data is AuthDataDto) {
            Response(status = status, message = message, data = data.toToken())
        } else {
            Response(status = status, message = message, data = null)
        }
    }
}
