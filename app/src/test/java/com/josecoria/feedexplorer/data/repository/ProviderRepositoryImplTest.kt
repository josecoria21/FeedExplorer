package com.josecoria.feedexplorer.data.repository

import com.josecoria.feedexplorer.data.remote.ProviderApi
import com.josecoria.feedexplorer.data.remote.dto.LocationDto
import com.josecoria.feedexplorer.data.remote.dto.ProviderDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ProviderRepositoryImplTest {

    private lateinit var api: ProviderApi
    private lateinit var repository: ProviderRepositoryImpl

    private val testDtos = listOf(
        ProviderDto(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            suffix = "MD",
            specialty = "Cardiology",
            npi = "1234567890",
            location = LocationDto(city = "Houston", state = "TX"),
            salaryRange = "$400,000 - $600,000",
            acceptingNewPatients = true
        )
    )

    @Before
    fun setup() {
        api = mockk()
        repository = ProviderRepositoryImpl(api)
    }

    @Test
    fun `getProviders returns mapped domain models on success`() = runTest {
        coEvery { api.getProviders() } returns testDtos

        val result = repository.getProviders()

        assertEquals(1, result.size)
        assertEquals("John", result[0].firstName)
        assertEquals("Doe", result[0].lastName)
        assertEquals("Cardiology", result[0].specialty)
    }

    @Test(expected = IOException::class)
    fun `getProviders throws exception when API fails`() = runTest {
        coEvery { api.getProviders() } throws IOException("Network error")

        repository.getProviders()
    }

    @Test
    fun `getProviders caches results after first call`() = runTest {
        coEvery { api.getProviders() } returns testDtos

        repository.getProviders()
        repository.getProviders()

        coVerify(exactly = 1) { api.getProviders() }
    }

    @Test
    fun `refreshProviders clears cache and fetches again`() = runTest {
        coEvery { api.getProviders() } returns testDtos

        repository.getProviders()
        repository.refreshProviders()

        coVerify(exactly = 2) { api.getProviders() }
    }
}
