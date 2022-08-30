package com.example.core_remote_config.data.usecase

import com.example.core_remote_config.domain.repository.RemoteConfigRepository
import com.example.core_remote_config.domain.usecases.GetRemoteConfigUseCase
import kotlinx.coroutines.flow.Flow

class GetRemoteConfigUseCaseImpl(
    private val repository: RemoteConfigRepository
) : GetRemoteConfigUseCase {

    override fun invoke(): Flow<Map<String, Any>> {
        return repository.config
    }
}