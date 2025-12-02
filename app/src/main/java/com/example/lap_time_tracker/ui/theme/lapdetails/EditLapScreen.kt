package com.example.lap_time_tracker.ui.theme.lapdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lap_time_tracker.data.LapTimeEntity
import com.example.lap_time_tracker.ui.theme.components.AppTopBar

@Composable
fun EditLapScreen(
    navController: NavController,
    lap: LapTimeEntity,
    onSave: (LapTimeEntity) -> Unit
) {
    Scaffold(
        topBar = { AppTopBar(navController) },
        containerColor = Color(0xFFD9D9D9)
    ) { padding ->

        var name by remember { mutableStateOf(lap.name) }
        var game by remember { mutableStateOf(lap.game) }
        var vehicle by remember { mutableStateOf(lap.vehicle) }
        var track by remember { mutableStateOf(lap.track) }
        var lapTime by remember { mutableStateOf(lap.lapTime) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                "Edit Lap",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(24.dp))

            EditField("Name", name) { name = it }
            EditField("Game", game) { game = it }
            EditField("Vehicle", vehicle) { vehicle = it }
            EditField("Track", track) { track = it }
            EditField("Lap Time", lapTime) { lapTime = it }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    val updatedLap = lap.copy(
                        name = name,
                        game = game,
                        vehicle = vehicle,
                        track = track,
                        lapTime = lapTime
                    )
                    onSave(updatedLap)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5A526F),
                    contentColor = Color.White
                )
            ) {
                Text("Save Changes")
            }
        }
    }
}

@Composable
fun EditField(label: String, text: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    )
}
