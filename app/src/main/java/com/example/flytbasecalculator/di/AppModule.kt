package com.example.flytbasecalculator.di

import com.example.flytbasecalculator.BuildConfig
import com.example.flytbasecalculator.data.remote.api.AuthenticationApi
import com.example.flytbasecalculator.data.remote.api.CalculationApi
import com.example.flytbasecalculator.data.remote.api.EndPoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Gson
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    //Retrofit Instance
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EndPoints.BASE_URL)
            .client(
                OkHttpClient.Builder().also {
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        it.addInterceptor(logging)
                        it.connectTimeout(120, TimeUnit.SECONDS)
                        it.readTimeout(120, TimeUnit.SECONDS)
                        it.protocols(Collections.singletonList(Protocol.HTTP_1_1))
                    }
                }.build()
            )
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    //Api
    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCalculatorApi(retrofit: Retrofit): CalculationApi {
        return retrofit.create(CalculationApi::class.java)
    }

}