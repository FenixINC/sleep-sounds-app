package com.example.core_remote_config.domain.repository

import com.example.core_remote_config.domain.entity.CacheHitResult
import kotlinx.coroutines.flow.SharedFlow

interface RemoteConfigRepository {

    val config: SharedFlow<Map<String, Any>>

    suspend fun init(): CacheHitResult

//    suspend fun load(): Result<CacheHitResult>
}