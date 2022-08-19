package com.example.core_remote_config.data.repository

import com.example.core_remote_config.data.source.RemoteConfigSource
import com.example.core_remote_config.domain.entity.CacheHitResult
import com.example.core_remote_config.domain.repository.RemoteConfigRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class RemoteConfigRepositoryImpl(
    private val source: RemoteConfigSource,
    private val defaultCoroutinesContext: CoroutineContext = Dispatchers.Default,
    private val ioCoroutinesContext: CoroutineContext = Dispatchers.IO
) : RemoteConfigRepository {

    override val config: SharedFlow<Map<String, Any>> = source.state

    override suspend fun init(): CacheHitResult {
        return withContext(defaultCoroutinesContext) {
            source.init()
        }
    }

//    override suspend fun load(): Result<CacheHitResult> {
//        return withContext(ioCoroutinesContext) {
//            source.load()
//        }
//    }
}