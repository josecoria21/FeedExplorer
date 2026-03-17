package com.josecoria.feedexplorer.domain.repository

import com.josecoria.feedexplorer.domain.model.Provider

interface ProviderRepository {
    suspend fun getProviders(): List<Provider>
    suspend fun refreshProviders(): List<Provider>
}
