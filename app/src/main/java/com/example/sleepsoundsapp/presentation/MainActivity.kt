package com.example.sleepsoundsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core_navigation.destination.SplashDestination
import com.example.sleepsoundsapp.navigation.addDestinations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
            val systemUiControllerState = rememberSystemUiController()
            val navControllerState = rememberAnimatedNavController()
            val navController = rememberNavController()


            ObserveEffect(
                mainViewModel = mainViewModel,
                navController = navControllerState,
            )

            Scaffold(
                topBar = {

                },
                content = {
                    Surface {
                        Content(navControllerState = navControllerState)
                    }
                },
                bottomBar = {

                }
            )
        }
    }

    @Composable
    private fun ObserveEffect(
        mainViewModel: MainViewModel,
        navController: NavController,
    ) {
        LaunchedEffect(navController) {
            mainViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is MainEffect.CloseApp -> {
                        this@MainActivity.finish()
                    }
                    is MainEffect.NavigateUp -> {
                        navController.navigateUp()
                    }
                    is MainEffect.Error -> {
                        when (effect.throwable) {
                            // TODO: show error
                        }
                    }
                    is MainEffect.Navigate -> {
                        navController.navigate(
                            route = effect.destination,
                            builder = effect.builder
                        )
                    }
                    is MainEffect.InternetConnection -> {
                        when (effect.isSuccess) {
                            true -> {
                                // TODO: success connect
                            }
                            false -> {
                                // TODO: show internet connection error
                            }
                        }
                    }
                }
            }
        }
    }

//    @Composable
//    private fun Content(navController: NavHostController) {
//        NavHost(
//            navController = navController,
//            startDestination = SplashDestination.route()
//        ) {
//            composable(route = SplashDestination.route()) {
//                SplashScreen()
//            }
//        }
//    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun Content(navControllerState: NavHostController) {
        AnimatedNavHost(
            navController = navControllerState,
            builder = {
                addDestinations()
            },
            startDestination = SplashDestination.route(),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(200)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(200)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(200)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(200)
                )
            }
        )
    }
}