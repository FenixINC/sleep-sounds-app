package com.example.sleepsoundsapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSource {

    fun loadData(): Flow<String> = flowOf(value = "Some new value")
}