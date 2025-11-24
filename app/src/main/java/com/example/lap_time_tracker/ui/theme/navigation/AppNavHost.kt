package com.example.lap_time_tracker.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lap_time_tracker.ui.theme.dashboard.DashboardScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(
                onAddLapClick = { navController.navigate("addLap") },
                onLapListClick = { navController.navigate("lapList") },
                onFavoritesClick = { navController.navigate("favorites") },
                onMenuClick = { /* TODO: open sidebar later */ },
                onProfileClick = { /* TODO: go to profile later */ }
            )
        }

        // These screens will be added next
        composable("addLap") {
            // TODO: Add Lap Screen
        }

        composable("lapList") {
            // TODO: Lap List Screen
        }

        composable("favorites") {
            // TODO: Favorites Screen
        }
    }
}