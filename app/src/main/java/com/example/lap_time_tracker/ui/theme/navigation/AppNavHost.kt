package com.example.lap_time_tracker.ui.theme.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lap_time_tracker.data.LapTimeEntity
import com.example.lap_time_tracker.ui.theme.dashboard.DashboardScreen
import com.example.lap_time_tracker.ui.theme.addlap.AddLapTimeScreen
import com.example.lap_time_tracker.ui.theme.addlap.AddLapViewModel
import com.example.lap_time_tracker.ui.theme.lapdetails.EditLapScreen
import com.example.lap_time_tracker.ui.theme.lapdetails.LapDetailsScreen
import com.example.lap_time_tracker.ui.theme.laplist.LapListScreen
import com.example.lap_time_tracker.ui.theme.laplist.LapListViewModel

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(navController)
        }

        // These screens will be added next
        composable("addLap") {
            val viewModel: AddLapViewModel = viewModel()


            AddLapTimeScreen(
                navController,
                onBack = { navController.popBackStack() },
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
                navController,
                viewModel = viewModel,
                onLapSelected = { lap ->
                    // Navigate to an Edit/Delete screen later
                    // For now:
                    println("Selected lap id = ${lap.id}")
                }
            )
        }



        composable("lapDetails/{lapId}") { entry ->
            val id = entry.arguments?.getString("lapId")?.toIntOrNull() ?: return@composable
            val viewModel: LapListViewModel = viewModel()

            val lapList = viewModel.allLaps.collectAsState(emptyList())
            val lap = lapList.value.firstOrNull { it.id == id }

            if (lap != null) {
                LapDetailsScreen(
                    navController = navController,
                    lap = lap, // NON-NULL
                    onEdit = {navController.navigate("editLap/${lap.id}")},
                    onDelete = {
                        viewModel.deleteLap(lap)
                        navController.popBackStack()
                    }
                )
            } else {
                Text("Lap not found")
            }
        }

        composable("editLap/{lapId}") { backStackEntry ->
            val lapId = backStackEntry.arguments?.getString("lapId")?.toIntOrNull()
            val viewModel: LapListViewModel = viewModel()

            // collect the list so we can find the lap
            val lapList = viewModel.allLaps.collectAsState(initial = emptyList())
            val lap = lapList.value.firstOrNull { it.id == lapId }

            if (lap != null) {
                EditLapScreen(
                    navController = navController,
                    lap = lap,
                    onSave = { updatedLap ->
                        viewModel.updateLap(updatedLap)
                        navController.popBackStack()   // go back
                    }
                )
            } else {
                Text("Lap not found = $lapId")
            }
        }
    }
}

