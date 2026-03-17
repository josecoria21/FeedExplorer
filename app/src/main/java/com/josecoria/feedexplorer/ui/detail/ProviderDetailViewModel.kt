package com.josecoria.feedexplorer.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.usecase.GetProviderByIdUseCase
import com.josecoria.feedexplorer.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProviderDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProviderByIdUseCase: GetProviderByIdUseCase
) : ViewModel() {

    private val providerId: Int = checkNotNull(savedStateHandle["providerId"])

    private val _uiState = MutableStateFlow<UiState<Provider>>(UiState.Loading)
    val uiState: StateFlow<UiState<Provider>> = _uiState.asStateFlow()

    init {
        loadProvider()
    }

    private fun loadProvider() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val provider = getProviderByIdUseCase(providerId)
                if (provider != null) {
                    _uiState.value = UiState.Success(provider)
                } else {
                    _uiState.value = UiState.Error("Provider not found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "An unexpected error occurred"
                )
            }
        }
    }
}
