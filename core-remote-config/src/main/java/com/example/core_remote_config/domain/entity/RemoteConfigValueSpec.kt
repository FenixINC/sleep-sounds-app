package com.example.core_remote_config.domain.entity

class RemoteConfigValueSpec<T : Any>(
    val key: String,
    val valueType: ValueType<T>,
    val defaultValueFactory: suspend () -> T
)