package com.example.core_remote_config.data.extensions

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal suspend fun <T> Task<T>.get(): T {
    return suspendCancellableCoroutine { continuation ->
        addOnCanceledListener { continuation.cancel() }
        addOnSuccessListener(continuation::resume)
        addOnFailureListener(continuation::resumeWithException)
    }
}