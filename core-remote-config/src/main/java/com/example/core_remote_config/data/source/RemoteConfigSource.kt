package com.example.core_remote_config.data.source

import com.example.core_remote_config.data.extensions.SuspendingLazy
import com.example.core_remote_config.data.extensions.get
import com.example.core_remote_config.data.factory.RemoteConfigFactory
import com.example.core_remote_config.data.mapper.RemoteConfigValuesSpecToValuesMapper
import com.example.core_remote_config.domain.entity.CacheHitResult
import com.example.core_remote_config.domain.entity.RemoteConfigValueSpec
import com.example.core_remote_config.domain.entity.fromIsCacheHit
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.shareIn

private const val FORCE_FETCH_MINIMUM_FETCH_INTERVAL_IN_SECONDS: Long = 60
private const val SHARED_STATE_REPLAYED_VALUES: Int = 1
private const val SHARED_STATE_REPLAY_EXPIRATION: Long = 0

class RemoteConfigSource(
    private val remoteConfigFactory: RemoteConfigFactory,
    private val valuesSpec: List<RemoteConfigValueSpec<*>>,
    private val mapper: RemoteConfigValuesSpecToValuesMapper,
    mainScope: CoroutineScope = MainScope()
) {

    private val remoteConfig = SuspendingLazy {
        remoteConfigFactory.create()
            .apply { read() }
    }

    private val _state: MutableStateFlow<Map<String, Any>> = MutableStateFlow(emptyMap())

    val state: SharedFlow<Map<String, Any>> = _state
        .filter { it.isNotEmpty() }
        .conflate()
        .shareIn(
            mainScope,
            started = SharingStarted.WhileSubscribed(
                replayExpirationMillis = SHARED_STATE_REPLAY_EXPIRATION
            ),
            replay = SHARED_STATE_REPLAYED_VALUES
        )

    suspend fun init(): CacheHitResult {
        val isInitialized = remoteConfig.isInitialized
        remoteConfig()
        return fromIsCacheHit(isInitialized)
    }

//    suspend fun loadIfNecessary(): Result<CacheHitResult> {
//        return loadInternal {
//            isCacheValid()
//                .also { fetch().get() }
//        }
//    }

//    suspend fun load(): Result<CacheHitResult> {
//        return loadInternal {
//            isCacheValid(FORCE_FETCH_MINIMUM_FETCH_INTERVAL_IN_SECONDS)
//                .also { fetch(FORCE_FETCH_MINIMUM_FETCH_INTERVAL_IN_SECONDS).get() }
//        }
//    }

    private suspend fun loadInternal(fetchStrategy: suspend FirebaseRemoteConfig.() -> Boolean): Result<CacheHitResult> {
        return remoteConfig().runCatching {
            val isCacheHit = fetchStrategy()
            if (activate().get()) {
                read()
            }
            fromIsCacheHit(isCacheHit)
        }
    }

    private suspend fun FirebaseRemoteConfig.read() {
        mapper(valuesSpec = valuesSpec, config = this)
            .also { _state.emit(it) }
    }

//    private fun FirebaseRemoteConfig.isCacheValid(
//        fetchIntervalSeconds: Long = info.configSettings.minimumFetchIntervalInSeconds
//    ): Boolean {
//        return currentTimeFactory() < Instant.fromEpochMilliseconds(info.fetchTimeMillis) +
//                fetchIntervalSeconds.seconds
//    }
}