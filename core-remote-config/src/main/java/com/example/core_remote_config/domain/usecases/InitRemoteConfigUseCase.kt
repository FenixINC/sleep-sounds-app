package com.example.core_remote_config.domain.usecases

import com.example.core_remote_config.domain.entity.CacheHitResult

fun interface InitRemoteConfigUseCase {

    suspend operator fun invoke(): CacheHitResult
}