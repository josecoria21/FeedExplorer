package com.josecoria.feedexplorer.ui.list

import app.cash.turbine.test
import com.josecoria.feedexplorer.MainDispatcherRule
import com.josecoria.feedexplorer.domain.model.Location
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.usecase.GetProvidersUseCase
import com.josecoria.feedexplorer.ui.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ProviderListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getProvidersUseCase: GetProvidersUseCase

    private val testProviders = listOf(
        Provider(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            suffix = "MD",
            specialty = "Cardiology",
            npi = "1234567890",
            location = Location(city = "Houston", state = "TX"),
            salaryRange = "$400,000 - $600,000",
            acceptingNewPatients = true
        ),
        Provider(
            id = 2,
            firstName = "Jane",
            lastName = "Smith",
            suffix = "DC",
            specialty = "Chiropractic",
            npi = "0987654321",
            location = Location(city = "Boise", state = "ID"),
            salaryRange = "$150,000 - $200,000",
            acceptingNewPatients = false
        )
    )

    @Before
    fun setup() {
        getProvidersUseCase = mockk()
    }

    @Test
    fun `init emits Success when use case succeeds`() = runTest {
        coEvery { getProvidersUseCase() } returns testProviders

        val viewModel = ProviderListViewModel(getProvidersUseCase)
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Success)
            assertEquals(testProviders, (state as UiState.Success).data)
        }
    }

    @Test
    fun `init emits Error when use case throws`() = runTest {
        coEvery { getProvidersUseCase() } throws IOException("Network error")

        val viewModel = ProviderListViewModel(getProvidersUseCase)
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Error)
            assertEquals("Network error", (state as UiState.Error).message)
        }
    }

    @Test
    fun `retry reloads providers successfully`() = runTest {
        coEvery { getProvidersUseCase() } throws IOException("Network error")

        val viewModel = ProviderListViewModel(getProvidersUseCase)
        advanceUntilIdle()

        coEvery { getProvidersUseCase() } returns testProviders

        viewModel.retry()
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Success)
        }
    }

    @Test
    fun `search filters providers by first name`() = runTest {
        coEvery { getProvidersUseCase() } returns testProviders

        val viewModel = ProviderListViewModel(getProvidersUseCase)
        advanceUntilIdle()

        viewModel.onSearchQueryChanged("John")
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Success)
            assertEquals(1, (state as UiState.Success).data.size)
            assertEquals("John", state.data[0].firstName)
        }
    }

    @Test
    fun `search filters providers by specialty`() = runTest {
        coEvery { getProvidersUseCase() } returns testProviders

        val viewModel = ProviderListViewModel(getProvidersUseCase)
        advanceUntilIdle()

        viewModel.onSearchQueryChanged("chiro")
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Success)
            assertEquals(1, (state as UiState.Success).data.size)
            assertEquals("Chiropractic", state.data[0].specialty)
        }
    }

    @Test
    fun `clearing search shows all providers`() = runTest {
        coEvery { getProvidersUseCase() } returns testProviders

        val viewModel = ProviderListViewModel(getProvidersUseCase)
        advanceUntilIdle()

        viewModel.onSearchQueryChanged("John")
        advanceUntilIdle()
        viewModel.onSearchQueryChanged("")
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is UiState.Success)
            assertEquals(2, (state as UiState.Success).data.size)
        }
    }
}
