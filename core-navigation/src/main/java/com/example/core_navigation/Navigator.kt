package com.example.core_navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface Navigator {
    val destinations: Flow<NavigatorEvent>

    fun onCloseApp(): Boolean

    fun onError(throwable: Throwable): Boolean

    fun navigateUp(): Boolean

    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean
}