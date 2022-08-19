package com.example.sleepsoundsapp.composer

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

object ComposerScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { ComposerViewModel() }
        val state by screenModel.state.collectAsState()

        Text(text = "Composer screen")
    }
}