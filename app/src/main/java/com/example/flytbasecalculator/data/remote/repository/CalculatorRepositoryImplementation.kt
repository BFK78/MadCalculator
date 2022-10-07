package com.example.flytbasecalculator.data.remote.repository

import com.example.flytbasecalculator.data.remote.api.CalculationApi
import com.example.flytbasecalculator.data.remote.dto.ExpressionDto
import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.CalculatorRepository
import javax.inject.Inject

class CalculatorRepositoryImplementation @Inject constructor(
    private val calculationApi: CalculationApi
): CalculatorRepository {

    override suspend fun insertExpression(expression: String, token: String): Response<Any> {
        return calculationApi.insertExpression(expression = expression, token = token)
    }

    override suspend fun getAllExpression(token: String): Response<ExpressionDto> {
        return calculationApi.getAllExpression(token)
    }

    override suspend fun deleteExpression(token: String): Response<Any> {
        return calculationApi.deleteAllExpression(token)
    }
}