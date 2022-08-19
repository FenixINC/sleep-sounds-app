package com.example.core_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<EVENT : ViewEvent, STATE : ViewState, EFFECT : ViewEffect> :
    ViewModel() {

    /**
     *     State – defines the state of the composable screen.
     *     It should dictate the content displayed on the screen, because it receives it as an input
     *     and passes it down to its descendants. The state mutates as the data loads and as the user interacts with it.
     *     The ViewModel is in charge of mutating and handling the state while the screen listens for changes.
     *
     *     Event – defines a certain user action e.g. click on a certain widget, pushing a button etc.
     *     The ViewModel should know how to act depending on the events it receives from the UI.
     *
     *     Effect – represents a side-effect action that should be consumed by the UI only once.
     *     The ViewModel can at any time decide that a side-effect should be caused,
     *     and the screen should know how to act on it.
     **/

    private val initialState: STATE by lazy { setInitialState() }
    abstract fun setInitialState(): STATE

    private val _viewState: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val viewState: StateFlow<STATE> = _viewState.asStateFlow()

    private val _effect: Channel<EFFECT> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect { event -> handleEvents(event = event) }
        }
    }

    abstract fun handleEvents(event: EVENT)

    fun setEvent(event: EVENT) {
        viewModelScope.launch { _event.emit(value = event) }
    }

    protected fun setState(reducer: STATE.() -> STATE) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    protected fun setEffect(builder: () -> EFFECT) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(element = effectValue) }
    }
}