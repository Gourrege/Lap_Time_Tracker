package com.example.lap_time_tracker.ui.theme.addlap


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLapTimeScreen(
    onBack: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSaveLap: (String, String, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var game by remember { mutableStateOf("") }
    var track by remember { mutableStateOf("") }
    var vehicle by remember { mutableStateOf("") }

    var minutes by remember { mutableStateOf("") }
    var seconds by remember { mutableStateOf("") }
    var millis by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DELTAHUB",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFD9D9D9)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Add Lap Time",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = minutes,
                    onValueChange = { minutes = it.filter { ch -> ch.isDigit() } },
                    modifier = Modifier.width(80.dp),
                    label = { Text("Min") },
                    singleLine = true
                )

                Text(":", fontSize = 28.sp)

                OutlinedTextField(
                    value = seconds,
                    onValueChange = { seconds = it.filter { ch -> ch.isDigit() } },
                    modifier = Modifier.width(80.dp),
                    label = { Text("Sec") },
                    singleLine = true
                )

                Text(":", fontSize = 28.sp)

                OutlinedTextField(
                    value = millis,
                    onValueChange = { millis = it.filter { ch -> ch.isDigit() } },
                    modifier = Modifier.width(100.dp),
                    label = { Text("Ms") },
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ------- TEXT FIELDS --------

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = game,
                onValueChange = { game = it },
                label = { Text("Game") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = track,
                onValueChange = { track = it },
                label = { Text("Track") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = vehicle,
                onValueChange = { vehicle = it },
                label = { Text("Vehicle") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Save button
            Button(
                onClick = {
                    val lapTime = "${minutes}:${seconds}:${millis}"
                    onSaveLap(name, game, track, vehicle, lapTime)
                    onBack()
                },
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5C5470),
                    contentColor = Color.White
                )
            ) {
                Text(text = "+  Add Lap Time", fontSize = 18.sp)
            }
        }
    }
}
