package com.example.domain.usecase

import com.example.domain.repository.ComposerRepository

interface GetListUseCase {
    suspend fun getList()
}

internal class GetListUseCaseImpl(
    private val repository: ComposerRepository
) : GetListUseCase {

    override suspend fun getList() {
        repository.getList()
    }
}