package com.josecoria.feedexplorer.data.di

import com.josecoria.feedexplorer.data.repository.ProviderRepositoryImpl
import com.josecoria.feedexplorer.domain.repository.ProviderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProviderRepository(
        impl: ProviderRepositoryImpl
    ): ProviderRepository
}
