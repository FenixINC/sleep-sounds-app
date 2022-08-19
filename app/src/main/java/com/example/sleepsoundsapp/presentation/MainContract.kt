package com.example.sleepsoundsapp.presentation

import androidx.navigation.NavOptionsBuilder
import com.example.core_viewmodel.ViewEffect
import com.example.core_viewmodel.ViewEvent
import com.example.core_viewmodel.ViewState

object MainState : ViewState

object MainEvent : ViewEvent

sealed class MainEffect : ViewEffect {
    data class Error(val throwable: Throwable) : MainEffect()
    data class Navigate(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit
    ) : MainEffect()

    object NavigateUp : MainEffect()
    object CloseApp : MainEffect()

    data class InternetConnection(val isSuccess: Boolean) : MainEffect()
}