package com.example.flytbasecalculator.data.remote.dto

data class ExpressionDto(
    val token: String,
    val calculations: List<CalculatorDto>
)
