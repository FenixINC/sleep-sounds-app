package com.example.core_navigation.destination

import com.example.core_navigation.NavigationDestination

private const val ROUTE_PROFILE = "route_profile"

object ProfileDestination : NavigationDestination {
    override fun route(): String = ROUTE_PROFILE
}