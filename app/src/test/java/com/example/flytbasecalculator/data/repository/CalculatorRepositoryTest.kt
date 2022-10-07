package com.example.flytbasecalculator.data.repository

import com.example.flytbasecalculator.data.remote.dto.ExpressionDto
import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.CalculatorRepository

class CalculatorRepositoryTest(): CalculatorRepository {
    override suspend fun insertExpression(expression: String, token: String): Response<Any> {
        return Response(false, "")
    }

    override suspend fun getAllExpression(token: String): Response<ExpressionDto> {
        return Response(false, "")
    }

    override suspend fun deleteExpression(token: String): Response<Any> {
        return Response(false, "")
    }

}