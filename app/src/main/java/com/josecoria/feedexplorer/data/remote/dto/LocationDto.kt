package com.josecoria.feedexplorer.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val city: String,
    val state: String
)
