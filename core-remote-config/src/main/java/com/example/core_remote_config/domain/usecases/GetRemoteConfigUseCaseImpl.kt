package com.example.core_remote_config.domain.usecases

import com.example.core_remote_config.domain.repository.RemoteConfigRepository
import kotlinx.coroutines.flow.Flow

internal class GetRemoteConfigUseCaseImpl(
    private val repository: RemoteConfigRepository
) : GetRemoteConfigUseCase {

    override fun invoke(): Flow<Map<String, Any>> {
        return repository.config
    }
}