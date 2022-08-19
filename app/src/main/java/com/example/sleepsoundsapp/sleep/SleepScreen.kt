package com.example.sleepsoundsapp.sleep

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.sleepsoundsapp.composer.ComposerViewModel

object SleepScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { ComposerViewModel() }
        val state by screenModel.state.collectAsState()

        Text(text = "Sleep screen")
    }
}