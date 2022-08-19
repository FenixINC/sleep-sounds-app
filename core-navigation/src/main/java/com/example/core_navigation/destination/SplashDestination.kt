package com.example.core_navigation.destination

import com.example.core_navigation.NavigationDestination

private const val ROUTE_SPLASH = "route_splash"

object SplashDestination : NavigationDestination {
    override fun route(): String = ROUTE_SPLASH
}