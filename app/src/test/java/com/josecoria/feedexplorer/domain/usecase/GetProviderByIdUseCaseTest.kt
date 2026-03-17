package com.josecoria.feedexplorer.domain.usecase

import com.josecoria.feedexplorer.domain.model.Location
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.domain.repository.ProviderRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetProviderByIdUseCaseTest {

    private lateinit var repository: ProviderRepository
    private lateinit var useCase: GetProviderByIdUseCase

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
        repository = mockk()
        useCase = GetProviderByIdUseCase(repository)
    }

    @Test
    fun `invoke returns correct provider when ID exists`() = runTest {
        coEvery { repository.getProviders() } returns testProviders

        val result = useCase(1)

        assertEquals("John", result?.firstName)
        assertEquals(1, result?.id)
    }

    @Test
    fun `invoke returns null when ID does not exist`() = runTest {
        coEvery { repository.getProviders() } returns testProviders

        val result = useCase(999)

        assertNull(result)
    }

    @Test(expected = IOException::class)
    fun `invoke propagates repository exception`() = runTest {
        coEvery { repository.getProviders() } throws IOException("Network error")

        useCase(1)
    }
}
