package com.example.domain.repository

import com.example.data.source.LocalSource

interface ComposerRepository {
    suspend fun getList()
}

internal class ComposerRepositoryImpl(
    private val localeSource: LocalSource
) : ComposerRepository {
    override suspend fun getList() {

    }
}