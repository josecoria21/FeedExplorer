package com.josecoria.feedexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.josecoria.feedexplorer.ui.navigation.FeedExplorerNavGraph
import com.josecoria.feedexplorer.ui.theme.FeedExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeedExplorerTheme {
                val navController = rememberNavController()
                FeedExplorerNavGraph(navController = navController)
            }
        }
    }
}
