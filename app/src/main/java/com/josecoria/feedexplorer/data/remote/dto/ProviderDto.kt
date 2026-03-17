package com.josecoria.feedexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProviderDto(
    val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val suffix: String,
    val specialty: String,
    val npi: String,
    val location: LocationDto,
    @SerialName("salary_range") val salaryRange: String,
    @SerialName("accepting_new_patients") val acceptingNewPatients: Boolean
)
