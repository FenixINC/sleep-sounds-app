package com.example.sleepsoundsapp.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nightlife
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.sleepsoundsapp.sleep.SleepScreen

object SleepTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Sleep"
            val icon = rememberVectorPainter(Icons.Default.Nightlife)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        SleepScreen.Content()
    }
}