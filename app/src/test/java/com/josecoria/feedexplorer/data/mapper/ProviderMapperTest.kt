package com.josecoria.feedexplorer.data.mapper

import com.josecoria.feedexplorer.data.remote.dto.LocationDto
import com.josecoria.feedexplorer.data.remote.dto.ProviderDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProviderMapperTest {

    @Test
    fun `toDomain maps all fields correctly`() {
        val dto = ProviderDto(
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

        val provider = dto.toDomain()

        assertEquals(1, provider.id)
        assertEquals("John", provider.firstName)
        assertEquals("Doe", provider.lastName)
        assertEquals("MD", provider.suffix)
        assertEquals("Cardiology", provider.specialty)
        assertEquals("1234567890", provider.npi)
        assertEquals("Houston", provider.location.city)
        assertEquals("TX", provider.location.state)
        assertEquals("$400,000 - $600,000", provider.salaryRange)
        assertEquals(true, provider.acceptingNewPatients)
    }

    @Test
    fun `toDomain handles special characters in names`() {
        val dto = ProviderDto(
            id = 2,
            firstName = "Duc Thinh",
            lastName = "O'Brien",
            suffix = "DC",
            specialty = "Chiropractic",
            npi = "9876543210",
            location = LocationDto(city = "Los Angeles", state = "CA"),
            salaryRange = "$150,000 - $200,000",
            acceptingNewPatients = false
        )

        val provider = dto.toDomain()

        assertEquals("Duc Thinh", provider.firstName)
        assertEquals("O'Brien", provider.lastName)
        assertEquals(false, provider.acceptingNewPatients)
    }

    @Test
    fun `LocationDto toDomain maps correctly`() {
        val locationDto = LocationDto(city = "Boise", state = "ID")

        val location = locationDto.toDomain()

        assertEquals("Boise", location.city)
        assertEquals("ID", location.state)
    }
}
