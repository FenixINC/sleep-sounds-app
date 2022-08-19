package com.example.sleepsoundsapp.composer

sealed class HomeState {
    object Init : HomeState()
    object Loading : HomeState()
}