package com.josecoria.feedexplorer.ui.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.josecoria.feedexplorer.MainDispatcherRule
import com.josecoria.feedexplorer.domain.model.Location
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.usecase.GetProviderByIdUseCase
import com.josecoria.feedexplorer.ui.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ProviderDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getProviderByIdUseCase: GetProviderByIdUseCase

    private val testProvider = Provider(
        id = 1,
        firstName = "John",
        lastName = "Doe",
        suffix = "MD",
        specialty = "Cardiology",
        npi = "1234567890",
        location = Location(city = "Houston", state = "TX"),
        salaryRange = "$400,000 - $600,000",
        acceptingNewPatients = true
    )

    @Before
    fun setup() {
        getProviderByIdUseCase = mockk()
    }

    private fun createSavedStateHandle(providerId: Int): SavedStateHandle {
        return SavedStateHandle(mapOf("providerId" to providerId))
    }

    @Test
    fun `init emits Success when provider found`() = runTest {
        coEvery { getProviderByIdUseCase(1) } returns testProvider

        val viewModel = ProviderDetailViewModel(
            savedStateHandle = createSavedStateHandle(1),
            getProviderByIdUseCase = getProviderByIdUseCase
        )

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Success)
            assertEquals(testProvider, (state as UiState.Success).data)
        }
    }

    @Test
    fun `init emits Error when provider not found`() = runTest {
        coEvery { getProviderByIdUseCase(999) } returns null

        val viewModel = ProviderDetailViewModel(
            savedStateHandle = createSavedStateHandle(999),
            getProviderByIdUseCase = getProviderByIdUseCase
        )

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Error)
        }
    }

    @Test
    fun `init emits Error when use case throws`() = runTest {
        coEvery { getProviderByIdUseCase(1) } throws IOException("Network error")

        val viewModel = ProviderDetailViewModel(
            savedStateHandle = createSavedStateHandle(1),
            getProviderByIdUseCase = getProviderByIdUseCase
        )

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Error)
            assertEquals("Network error", (state as UiState.Error).message)
        }
    }
}
