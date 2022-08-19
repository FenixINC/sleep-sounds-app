package com.example.core_remote_config.domain.entity

import kotlinx.serialization.KSerializer

sealed interface ValueType<T> {
    object BOOLEAN : ValueType<Boolean>
    object LONG : ValueType<Long>
    object STRING : ValueType<String>
    object DOUBLE : ValueType<Double>
    class JSON<T>(
        val serializer: KSerializer<T>
    ) : ValueType<T>
}