package com.example.core_remote_config.data.usecase

import com.example.core_remote_config.domain.entity.CacheHitResult
import com.example.core_remote_config.domain.repository.RemoteConfigRepository
import com.example.core_remote_config.domain.usecases.InitRemoteConfigUseCase

internal class InitRemoteConfigUseCaseImpl(
    private val repository: RemoteConfigRepository
) : InitRemoteConfigUseCase {

    override suspend fun invoke(): CacheHitResult {
        return repository.init()
    }
}