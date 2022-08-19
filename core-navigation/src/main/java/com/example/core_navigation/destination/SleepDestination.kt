package com.example.core_navigation.destination

import com.example.core_navigation.NavigationDestination

private const val ROUTE_SLEEP = "route_sleep"

object SleepDestination : NavigationDestination {
    override fun route(): String = ROUTE_SLEEP
}