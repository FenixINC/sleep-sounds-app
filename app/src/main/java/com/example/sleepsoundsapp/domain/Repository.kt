package com.example.sleepsoundsapp.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getData(): Flow<String>
    suspend fun loadData()
}