package com.josecoria.feedexplorer.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object ProviderList : Screen

    @Serializable
    data class ProviderDetail(val providerId: Int) : Screen
}
