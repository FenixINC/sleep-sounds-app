package com.example.core_remote_config.data.factory

import com.example.core_remote_config.data.extensions.get
import com.example.core_remote_config.data.mapper.RemoteConfigValuesSpecToDefaultValuesMapper
import com.example.core_remote_config.domain.entity.RemoteConfigValueSpec
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val CONNECTION_TIMEOUT_IN_SECONDS: Long = 15
private const val MINIMUM_FETCH_INTERVAL_IN_SECONDS = 3600L

class RemoteConfigFactory(
    private val valuesSpec: List<RemoteConfigValueSpec<*>>,
    private val mapper: RemoteConfigValuesSpecToDefaultValuesMapper,
    private val remoteConfigFactory: () -> FirebaseRemoteConfig = { Firebase.remoteConfig }
) {
    suspend fun create(): FirebaseRemoteConfig {
        return remoteConfigFactory().apply {
            setSettings()
            setDefaults()
            activateIfNecessary()
        }
    }

    private suspend fun FirebaseRemoteConfig.setSettings() {
        setConfigSettingsAsync(remoteConfigSettings {
            fetchTimeoutInSeconds = CONNECTION_TIMEOUT_IN_SECONDS
            minimumFetchIntervalInSeconds = MINIMUM_FETCH_INTERVAL_IN_SECONDS
        }).get()
    }

    private suspend fun FirebaseRemoteConfig.setDefaults() {
        mapper(valuesSpec)
            .let(::setDefaultsAsync)
            .get()
    }

    private suspend fun FirebaseRemoteConfig.activateIfNecessary() {
        if (info.lastFetchStatus == FirebaseRemoteConfig.LAST_FETCH_STATUS_SUCCESS) {
            activate().get()
        }
    }
}