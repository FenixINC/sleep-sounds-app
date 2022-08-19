package com.example.core_remote_config.domain.usecases

import kotlinx.coroutines.flow.Flow

interface GetRemoteConfigUseCase : () -> Flow<Map<String, Any>>