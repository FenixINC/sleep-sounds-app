package com.example.domain.di

import com.example.domain.usecase.GetListUseCase
import com.example.domain.usecase.GetListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun provideListUseCase(getListUseCase: GetListUseCaseImpl): GetListUseCase
}