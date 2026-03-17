package com.josecoria.feedexplorer.data.remote

import com.josecoria.feedexplorer.data.remote.dto.ProviderDto
import retrofit2.http.GET

interface ProviderApi {

    @GET("v1/5bb09ab0-8d6d-4d85-8284-b6a467299353")
    suspend fun getProviders(): List<ProviderDto>
}
