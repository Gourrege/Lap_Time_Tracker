package com.example.lap_time_tracker.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lap_time_tracker.ui.theme.dashboard.DashboardScreen
import com.example.lap_time_tracker.ui.theme.addlap.AddLapTimeScreen

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
            AddLapTimeScreen(
                onBack = { navController.popBackStack() },
                onMenuClick = { /* later */ },
                onProfileClick = { /* later */ },
                onSaveLap = { name, game, track, vehicle, lapTimeString ->
                    // TODO: Save to ROOM next step
                }
            )
        }

        composable("lapList") {
            // TODO: Lap List Screen
        }

        composable("favorites") {
            // TODO: Favorites Screen
        }
    }
}