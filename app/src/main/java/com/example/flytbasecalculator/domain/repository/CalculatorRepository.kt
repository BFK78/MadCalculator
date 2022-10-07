package com.example.flytbasecalculator.domain.repository

import com.example.flytbasecalculator.data.remote.dto.ExpressionDto
import com.example.flytbasecalculator.data.remote.dto.Response

interface CalculatorRepository {

    suspend fun insertExpression(expression:String, token: String): Response<Any>

    suspend fun getAllExpression(token: String): Response<ExpressionDto>

    suspend fun deleteExpression(token: String): Response<Any>
}