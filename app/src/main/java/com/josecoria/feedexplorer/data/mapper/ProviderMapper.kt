package com.josecoria.feedexplorer.data.mapper

import com.josecoria.feedexplorer.data.remote.dto.LocationDto
import com.josecoria.feedexplorer.data.remote.dto.ProviderDto
import com.josecoria.feedexplorer.domain.model.Location
import com.josecoria.feedexplorer.domain.model.Provider

fun ProviderDto.toDomain(): Provider {
    return Provider(
        id = id,
        firstName = firstName,
        lastName = lastName,
        suffix = suffix,
        specialty = specialty,
        npi = npi,
        location = location.toDomain(),
        salaryRange = salaryRange,
        acceptingNewPatients = acceptingNewPatients
    )
}

fun LocationDto.toDomain(): Location {
    return Location(
        city = city,
        state = state
    )
}
