package com.example.core_remote_config.domain.entity

enum class CacheHitResult {
    CACHE_HIT,
    CACHE_MISS
}

fun fromIsCacheHit(isCacheHit: Boolean): CacheHitResult {
    return if (isCacheHit) {
        CacheHitResult.CACHE_HIT
    } else {
        CacheHitResult.CACHE_MISS
    }
}