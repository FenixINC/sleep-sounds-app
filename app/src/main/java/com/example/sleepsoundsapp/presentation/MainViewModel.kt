package com.example.sleepsoundsapp.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_navigation.Navigator
import com.example.core_navigation.NavigatorEvent
import com.example.core_viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator
) : BaseViewModel<MainEvent, MainState, MainEffect>() {

    init {
        handleDestination()
    }

    override fun setInitialState(): MainState = MainState

    override fun handleEvents(event: MainEvent) {
        when (event) {

        }
    }

    private fun handleDestination() {
        viewModelScope.launch {
            navigator.destinations.collectLatest { navigationEvent ->
                when (navigationEvent) {
                    is NavigatorEvent.CloseApp -> setEffect { MainEffect.CloseApp }
                    is NavigatorEvent.NavigateUp -> setEffect { MainEffect.NavigateUp }
                    is NavigatorEvent.Error -> {
                        // TODO: show error
                    }
//                    is NavigatorEvent.InternetConnectionState -> {
//                        when (navigationEvent.networkStatusResultState) {
//                            is NetworkStatusResultState.Success -> {
//                                setEffect { MainEffect.InternetConnection(isSuccess = true) }
//                            }
//                            is NetworkStatusResultState.Error -> {
//                                setEffect { MainEffect.InternetConnection(isSuccess = false) }
//                            }
//                        }
//                    }
                    is NavigatorEvent.Directions -> {
                        setEffect {
                            MainEffect.Navigate(
                                destination = navigationEvent.destination,
                                builder = navigationEvent.builder
                            )
                        }
                    }
                }
            }
        }
    }
}