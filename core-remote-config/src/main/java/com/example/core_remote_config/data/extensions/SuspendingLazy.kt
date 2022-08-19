package com.example.core_remote_config.data.extensions

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SuspendingLazy<T>(initializer: Initializer<T>) {
    @Volatile private var initializer: Initializer<T>? = initializer
    @Volatile private var mutex: Mutex? = Mutex()
    @Volatile private var _value: T? = null

    val isInitialized: Boolean get() = initializer == null

    suspend operator fun invoke(): T {
        mutex?.withLock {
            initializer
                ?.invoke()
                ?.also { value ->
                    _value = value
                    initializer = null
                    mutex = null
                }
        }
        return requireNotNull(_value)
    }

    fun interface Initializer<T> {
        suspend fun invoke(): T
    }
}