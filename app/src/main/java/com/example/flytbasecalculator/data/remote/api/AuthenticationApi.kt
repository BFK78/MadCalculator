package com.example.flytbasecalculator.data.remote.api

import com.example.flytbasecalculator.data.remote.dto.AuthDataDto
import com.example.flytbasecalculator.data.remote.dto.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationApi {

    @FormUrlEncoded
    @POST(EndPoints.LOGIN_URL)
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthDataDto>

}
