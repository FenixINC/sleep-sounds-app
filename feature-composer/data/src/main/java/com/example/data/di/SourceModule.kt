package com.example.data.di

import com.example.data.source.LocalSource
import com.example.data.source.RemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object SourceModule {

    @Provides
    fun provideLocalSource() = LocalSource()

    @Provides
    fun provideRemoteSource() = RemoteSource()
}