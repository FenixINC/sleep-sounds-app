package com.example.core_remote_config.data.mapper

import com.example.core_remote_config.domain.entity.RemoteConfigValueSpec
import com.example.core_remote_config.domain.entity.ValueType
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import kotlinx.serialization.json.Json
import timber.log.Timber

class RemoteConfigValuesSpecToValuesMapper(
    private val json: Json
) {
    suspend operator fun invoke(
        valuesSpec: List<RemoteConfigValueSpec<*>>,
        config: FirebaseRemoteConfig
    ): Map<String, Any> {
        return valuesSpec.associate { valueSpec ->
            valueSpec.key to config.getValue(valueSpec)
        }
    }

    private suspend inline fun <T : Any> FirebaseRemoteConfig.getValue(spec: RemoteConfigValueSpec<T>): Any {
        return getValue(spec.key)
            .let { runCatching { it.get(spec.valueType) } }
            .onFailure { e -> Timber.e(e, "Failed to parse value for ${spec.key}") }
            .recover { spec.defaultValueFactory() }
            .getOrThrow()
    }

    private fun <T : Any> FirebaseRemoteConfigValue.get(valueType: ValueType<T>): Any {
        return when (valueType) {
            ValueType.BOOLEAN -> asBoolean()
            ValueType.LONG -> asLong()
            ValueType.STRING -> asString()
            ValueType.DOUBLE -> asDouble()
            is ValueType.JSON -> json.decodeFromString(
                deserializer = valueType.serializer,
                asString()
            )
        }
    }
}