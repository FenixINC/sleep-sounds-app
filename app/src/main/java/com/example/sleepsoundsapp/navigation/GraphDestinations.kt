package com.example.sleepsoundsapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.example.core_navigation.NavigationDestination
import com.example.core_navigation.destination.ComposerDestination
import com.example.core_navigation.destination.SleepDestination
import com.example.core_navigation.destination.SplashDestination
import com.example.feature_sleep.presentation.SleepScreen
import com.example.feature_splash.presentation.SplashScreen
import com.example.presentation.ComposerScreen
import com.google.accompanist.navigation.animation.composable

private fun getDestinations(): Map<NavigationDestination, @Composable (NavBackStackEntry) -> Unit> =
    mapOf(
        SplashDestination to { SplashScreen() },
        SleepDestination to { SleepScreen() },
        ComposerDestination to { ComposerScreen() },
//        CollectionDetailsDestination to { navBackStackEntry ->
//            val collectionId = navBackStackEntry
//                .arguments
//                ?.getString(ARG_COLLECTION_ID)
//                ?: EMPTY_TEXT
//
//            CollectionDetailsScreen(collectionId = collectionId)
//        }
    )

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addDestinations() {
    getDestinations().forEach { entry ->
        val destination = entry.key

        composable(
            destination.route(),
            destination.arguments,
            destination.deepLinks
        ) { navBackStackEntry ->
            entry.value(navBackStackEntry)
        }
    }
}