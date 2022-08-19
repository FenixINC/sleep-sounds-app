package com.example.sleepsoundsapp.data

import com.example.sleepsoundsapp.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class RepositoryImpl(
    private val localeDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getData(): Flow<String> {
        return localeDataSource
            .dataFlow
            .onEach { result ->
                loadData()
            }
    }

    override suspend fun loadData() {
        remoteDataSource
            .loadData()
            .also {
                localeDataSource.saveData()
            }
    }
}