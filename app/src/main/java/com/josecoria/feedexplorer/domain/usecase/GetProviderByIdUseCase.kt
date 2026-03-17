package com.josecoria.feedexplorer.domain.usecase

import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.repository.ProviderRepository
import javax.inject.Inject

class GetProviderByIdUseCase @Inject constructor(
    private val repository: ProviderRepository
) {
    suspend operator fun invoke(id: Int): Provider? {
        return repository.getProviders().find { it.id == id }
    }
}
