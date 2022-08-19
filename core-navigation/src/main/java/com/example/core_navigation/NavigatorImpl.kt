package com.example.core_navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor() : Navigator {

    private val navigationEvents = Channel<NavigatorEvent>()

    override val destinations = navigationEvents.receiveAsFlow()

    override fun onCloseApp(): Boolean =
        navigationEvents.trySend(element = NavigatorEvent.CloseApp).isSuccess

    override fun onError(throwable: Throwable): Boolean =
        navigationEvents.trySend(element = NavigatorEvent.Error(throwable = throwable)).isSuccess

    override fun navigateUp(): Boolean =
        navigationEvents.trySend(element = NavigatorEvent.NavigateUp).isSuccess

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
        navigationEvents.trySend(element = NavigatorEvent.Directions(route, builder)).isSuccess
}