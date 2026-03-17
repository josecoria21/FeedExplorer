package com.josecoria.feedexplorer.domain.model

data class Provider(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val suffix: String,
    val specialty: String,
    val npi: String,
    val location: Location,
    val salaryRange: String,
    val acceptingNewPatients: Boolean
)
