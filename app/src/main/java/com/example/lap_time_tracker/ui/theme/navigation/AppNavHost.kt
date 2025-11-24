package com.example.lap_time_tracker.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lap_time_tracker.ui.theme.dashboard.DashboardScreen
import com.example.lap_time_tracker.ui.theme.addlap.AddLapTimeScreen
import com.example.lap_time_tracker.ui.theme.addlap.AddLapViewModel
import com.example.lap_time_tracker.ui.theme.laplist.LapListScreen
import com.example.lap_time_tracker.ui.theme.laplist.LapListViewModel

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
            val viewModel: AddLapViewModel = viewModel()


            AddLapTimeScreen(
                onBack = { navController.popBackStack() },
                onMenuClick = { /* later */ },
                onProfileClick = { /* later */ },
                onSaveLap = { name, game, track, vehicle, lapTime ->
                    viewModel.saveLap(name, game,track,vehicle, lapTime )

                    navController.navigate("lapList"){
                        popUpTo("dashboard") {inclusive = false}
                    }
                }
            )
        }

        composable("lapList") {
            val viewModel: LapListViewModel = viewModel()

            LapListScreen(
                viewModel = viewModel,
                onMenuClick = { /* later */ },
                onProfileClick = { /* later */ },
                onLapSelected = { lap ->
                    // Navigate to an Edit/Delete screen later
                    // For now:
                    println("Selected lap id = ${lap.id}")
                }
            )
        }

        composable("favorites") {
            // TODO: Favorites Screen
        }
    }
}