package com.example.flytbasecalculator.presentation.screens.authentication.util

import com.example.flytbasecalculator.domain.model.authentication.Token

data class AuthState(
    val loading: Boolean = false,
    val message: String? = null,
    val status: Boolean = false,
    val data: Token? = null
)
