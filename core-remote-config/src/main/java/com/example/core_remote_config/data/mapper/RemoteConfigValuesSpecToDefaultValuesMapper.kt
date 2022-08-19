package com.example.core_remote_config.data.mapper

import com.example.core_remote_config.domain.entity.RemoteConfigValueSpec
import com.example.core_remote_config.domain.entity.ValueType
import kotlinx.serialization.json.Json

class RemoteConfigValuesSpecToDefaultValuesMapper(
    private val json: Json
) {
    suspend operator fun invoke(valuesSpec: List<RemoteConfigValueSpec<*>>): Map<String, Any> {
        return valuesSpec.associate { valueSpec ->
            valueSpec.key to valueSpec.createDefaultValue()
        }
    }

    private suspend inline fun <T : Any> RemoteConfigValueSpec<T>.createDefaultValue(): Any {
        return defaultValueFactory().let { value ->
            when (valueType) {
                is ValueType.JSON -> json.encodeToString(
                    valueType.serializer,
                    value
                )
                else -> value
            }
        }
    }
}