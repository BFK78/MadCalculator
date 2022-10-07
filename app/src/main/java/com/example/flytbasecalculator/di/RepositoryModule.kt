package com.example.flytbasecalculator.di

import com.example.flytbasecalculator.data.remote.repository.AuthenticationRepositoryImplementation
import com.example.flytbasecalculator.data.remote.repository.CalculatorRepositoryImplementation
import com.example.flytbasecalculator.domain.repository.AuthenticationRepository
import com.example.flytbasecalculator.domain.repository.CalculatorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticationRepository(authenticationRepositoryImplementation: AuthenticationRepositoryImplementation): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindCalculatorRepository(calculatorRepositoryImplementation: CalculatorRepositoryImplementation): CalculatorRepository

}