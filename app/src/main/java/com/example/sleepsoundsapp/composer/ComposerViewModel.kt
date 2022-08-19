package com.example.sleepsoundsapp.composer

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.launch

class ComposerViewModel : StateScreenModel<HomeState>(initialState = HomeState.Init) {

    fun load() {
        coroutineScope.launch {
            mutableState.value = HomeState.Loading
        }
    }
}