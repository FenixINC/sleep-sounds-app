package com.example.feature_splash.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Splash")

        Button(
            onClick = { splashViewModel.setEvent(event = SplashEvent.OpenSleepScreen) }
        ) {
            Text(text = "Open Sleep Screen")
        }
    }
}