package com.example.flytbasecalculator.data.remote.api

import com.example.flytbasecalculator.data.remote.dto.ExpressionDto
import com.example.flytbasecalculator.data.remote.dto.Response
import retrofit2.http.*

interface CalculationApi {

    @FormUrlEncoded
    @POST(EndPoints.INSERT_EXPRESSION_URL)
    suspend fun insertExpression(
        @Field("expression") expression: String,
        @Field("token") token: String
    ): Response<Any>

    @GET(EndPoints.GET_EXPRESSION_URL)
    suspend fun getAllExpression(
        @Query("id") token: String
    ): Response<ExpressionDto>

    @FormUrlEncoded
    @POST(EndPoints.DELETE_EXPRESSION_URL)
    suspend fun deleteAllExpression(
        @Field("token") token: String
    ): Response<Any>

}