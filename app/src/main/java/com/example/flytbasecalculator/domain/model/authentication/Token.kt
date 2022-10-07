package com.example.flytbasecalculator.domain.model.authentication

data class Token(
    val token: String,
    val user: User,
    val id: Int = 1
)
