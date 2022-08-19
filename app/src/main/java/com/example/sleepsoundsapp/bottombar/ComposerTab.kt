package com.example.sleepsoundsapp.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.sleepsoundsapp.composer.ComposerScreen

object ComposerTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Composer"
            val icon = rememberVectorPainter(Icons.Default.MusicVideo)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        ComposerScreen.Content()
    }
}