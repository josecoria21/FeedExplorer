package com.josecoria.feedexplorer.domain.usecase

import com.josecoria.feedexplorer.domain.model.Location
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.repository.ProviderRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetProvidersUseCaseTest {

    private lateinit var repository: ProviderRepository
    private lateinit var useCase: GetProvidersUseCase

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
        )
    )

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetProvidersUseCase(repository)
    }

    @Test
    fun `invoke returns providers from repository`() = runTest {
        coEvery { repository.getProviders() } returns testProviders

        val result = useCase()

        assertEquals(testProviders, result)
    }

    @Test(expected = IOException::class)
    fun `invoke propagates repository exception`() = runTest {
        coEvery { repository.getProviders() } throws IOException("Network error")

        useCase()
    }
}
