package com.josecoria.feedexplorer.data.repository

import com.josecoria.feedexplorer.data.mapper.toDomain
import com.josecoria.feedexplorer.data.remote.ProviderApi
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.repository.ProviderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProviderRepositoryImpl @Inject constructor(
    private val api: ProviderApi
) : ProviderRepository {

    private var cachedProviders: List<Provider>? = null

    override suspend fun getProviders(): List<Provider> {
        cachedProviders?.let { return it }
        return fetchAndCache()
    }

    override suspend fun refreshProviders(): List<Provider> {
        cachedProviders = null
        return fetchAndCache()
    }

    private suspend fun fetchAndCache(): List<Provider> {
        val providers = api.getProviders().map { it.toDomain() }
        cachedProviders = providers
        return providers
    }
}
