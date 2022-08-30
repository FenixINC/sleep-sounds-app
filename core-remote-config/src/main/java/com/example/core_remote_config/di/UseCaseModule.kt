package com.example.core_remote_config.di

import com.example.core_remote_config.domain.usecases.GetRemoteConfigUseCase
import com.example.core_remote_config.data.usecase.GetRemoteConfigUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetRemoteConfigUseCase(
        getRemoteConfigUseCaseImpl: GetRemoteConfigUseCaseImpl
    ): GetRemoteConfigUseCase
}