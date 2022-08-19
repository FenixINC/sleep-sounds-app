package com.example.core_remote_config.di

import com.example.core_remote_config.data.factory.RemoteConfigFactory
import com.example.core_remote_config.data.mapper.RemoteConfigValuesSpecToDefaultValuesMapper
import com.example.core_remote_config.data.mapper.RemoteConfigValuesSpecToValuesMapper
import com.example.core_remote_config.data.repository.RemoteConfigRepositoryImpl
import com.example.core_remote_config.data.source.RemoteConfigSource
import com.example.core_remote_config.domain.entity.RemoteConfigValueSpec
import com.example.core_remote_config.domain.repository.RemoteConfigRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {

    @Provides
    fun provideRemoteConfigSpecToDefaultMapper(json: Json) =
        RemoteConfigValuesSpecToDefaultValuesMapper(json = json)

    @Provides
    fun provideRemoteConfigSpecToValueMapper(json: Json) =
        RemoteConfigValuesSpecToValuesMapper(json = json)

    @Provides
    fun provideRemoteConfigFactory(
        valuesSpec: List<RemoteConfigValueSpec<*>>,
        mapper: RemoteConfigValuesSpecToDefaultValuesMapper
    ) = RemoteConfigFactory(
        valuesSpec = valuesSpec,
        mapper = mapper,
    )

    @Provides
    fun provideRemoteConfigSource(
        remoteConfigFactory: RemoteConfigFactory,
        valuesSpec: List<RemoteConfigValueSpec<*>>,
        mapper: RemoteConfigValuesSpecToValuesMapper
    ) = RemoteConfigSource(
        remoteConfigFactory = remoteConfigFactory,
        valuesSpec = valuesSpec,
        mapper = mapper
    )
}