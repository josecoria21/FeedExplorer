package com.josecoria.feedexplorer.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.usecase.GetProvidersUseCase
import com.josecoria.feedexplorer.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProviderListViewModel @Inject constructor(
    private val getProvidersUseCase: GetProvidersUseCase
) : ViewModel() {

    private val _allProviders = MutableStateFlow<UiState<List<Provider>>>(UiState.Loading)

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    val uiState: StateFlow<UiState<List<Provider>>> = combine(
        _allProviders,
        _searchQuery
    ) { state, query ->
        when (state) {
            is UiState.Success -> {
                if (query.isBlank()) {
                    state
                } else {
                    val filtered = state.data.filter { provider ->
                        provider.firstName.contains(query, ignoreCase = true) ||
                            provider.lastName.contains(query, ignoreCase = true) ||
                            provider.specialty.contains(query, ignoreCase = true)
                    }
                    UiState.Success(filtered)
                }
            }
            else -> state
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading
    )

    init {
        loadProviders()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val providers = getProvidersUseCase.refresh()
                _allProviders.value = UiState.Success(providers)
            } catch (e: Exception) {
                _allProviders.value = UiState.Error(
                    e.message ?: "An unexpected error occurred",
                    errorType = e.cause
                )
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun retry() {
        loadProviders()
    }

    private fun loadProviders() {
        viewModelScope.launch {
            _allProviders.value = UiState.Loading
            try {
                val providers = getProvidersUseCase()
                _allProviders.value = UiState.Success(providers)
            } catch (e: Exception) {
                _allProviders.value = UiState.Error(
                    e.message ?: "An unexpected error occurred",
                    errorType = e.cause
                )
            }
        }
    }
}
