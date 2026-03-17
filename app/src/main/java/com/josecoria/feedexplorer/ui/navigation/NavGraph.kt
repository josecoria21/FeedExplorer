package com.josecoria.feedexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.josecoria.feedexplorer.ui.detail.ProviderDetailScreen
import com.josecoria.feedexplorer.ui.list.ProviderListScreen

@Composable
fun FeedExplorerNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProviderList
    ) {
        composable<Screen.ProviderList> {
            ProviderListScreen(
                onProviderClick = { provider ->
                    navController.navigate(Screen.ProviderDetail(providerId = provider.id))
                }
            )
        }
        composable<Screen.ProviderDetail> {
            ProviderDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
