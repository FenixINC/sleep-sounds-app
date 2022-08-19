package com.example.core_remote_config.di

import com.example.core_remote_config.data.repository.RemoteConfigRepositoryImpl
import com.example.core_remote_config.domain.repository.RemoteConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteConfigRepository(
        repository: RemoteConfigRepositoryImpl
    ): RemoteConfigRepository
}