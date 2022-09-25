package com.example.domain.di

import com.example.domain.repository.ComposerRepository
import com.example.domain.repository.ComposerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun provideComposerRepository(composerRepository: ComposerRepositoryImpl): ComposerRepository
}