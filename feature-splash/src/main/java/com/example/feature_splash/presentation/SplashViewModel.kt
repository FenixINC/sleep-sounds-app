package com.example.feature_splash.presentation

import com.example.core_navigation.Navigator
import com.example.core_navigation.destination.SleepDestination
import com.example.core_viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
) : BaseViewModel<SplashEvent, SplashState, SplashEffect>(), Navigator by navigator {

    override fun setInitialState(): SplashState = SplashState

    override fun handleEvents(event: SplashEvent) {
        when (event) {
            is SplashEvent.OpenSleepScreen -> {
                navigator.navigate(route = SleepDestination.route())
            }
        }
    }
}