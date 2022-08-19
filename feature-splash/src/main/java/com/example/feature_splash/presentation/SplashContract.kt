package com.example.feature_splash.presentation

import com.example.core_viewmodel.ViewEffect
import com.example.core_viewmodel.ViewEvent
import com.example.core_viewmodel.ViewState

object SplashState : ViewState

sealed class SplashEvent : ViewEvent {
    object OpenSleepScreen : SplashEvent()
}

object SplashEffect : ViewEffect