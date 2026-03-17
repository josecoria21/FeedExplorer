package com.josecoria.feedexplorer.domain.usecase

import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.repository.ProviderRepository
import javax.inject.Inject

/**
 * This whole use case can be deleted and injected in the viewmodel
 * it is not doing any real logic her, just passing the list of providers
 */
class GetProvidersUseCase @Inject constructor(
    private val repository: ProviderRepository
) {
    suspend operator fun invoke(): List<Provider> {
        return repository.getProviders()
    }

    suspend fun refresh(): List<Provider> {
        return repository.refreshProviders()
    }
}
