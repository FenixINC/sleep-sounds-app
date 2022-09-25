package com.example.core_navigation.destination

import com.example.core_navigation.NavigationDestination

private const val ROUTE_COMPOSER = "route_composer"

object ComposerDestination : NavigationDestination {
    override fun route(): String = ROUTE_COMPOSER
}