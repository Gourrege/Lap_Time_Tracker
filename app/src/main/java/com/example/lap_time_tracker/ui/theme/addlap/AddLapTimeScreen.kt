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

    var expandedGame by remember { mutableStateOf(false) }
    var selectedGame by remember { mutableStateOf("") }

    var expandedTrack by remember { mutableStateOf(false) }
    var selectedTrack by remember { mutableStateOf("") }

    var expandedVehicle by remember { mutableStateOf(false) }
    var selectedVehicle by remember { mutableStateOf("") }

    var minutes by remember { mutableStateOf("") }
    var seconds by remember { mutableStateOf("") }
    var millis by remember { mutableStateOf("") }

    // --- GAME OPTIONS ---
    val gameOptions = listOf("F1 24", "Assetto Corsa")

// --- TRACK OPTIONS (same list for all games) ---
    val trackOptions = listOf(
        "Bahrain International Circuit (Bahrain)",
        "Jeddah Corniche Circuit (Saudi Arabia)",
        "Albert Park Circuit (Australia)",
        "Suzuka International Racing Course (Japan)",
        "Shanghai International Circuit (China)",
        "Miami International Autodrome (USA)",
        "Autodromo Internazionale Enzo e Dino Ferrari (Italy)",
        "Circuit de Monaco (Monaco)",
        "Circuit Gilles-Villeneuve (Canada)",
        "Circuit de Barcelona-Catalunya (Spain)",
        "Red Bull Ring (Austria)",
        "Silverstone Circuit (UK)",
        "Hungaroring (Hungary)",
        "Circuit de Spa-Francorchamps (Belgium)",
        "Circuit Zandvoort (Netherlands)",
        "Autodromo Nazionale Monza (Italy)",
        "Baku City Circuit (Azerbaijan)",
        "Marina Bay Street Circuit (Singapore)",
        "Circuit of the Americas (USA)",
        "Autódromo Hermanos Rodríguez (Mexico)",
        "Autódromo José Carlos Pace (Brazil)",
        "Las Vegas Strip Street Circuit (USA)",
        "Lusail International Circuit (Qatar)",
        "Yas Marina Circuit (Abu Dhabi)",
        "Algarve International Circuit (Portugal)",
        "Pikes Peak International Hill Climb Course (USA)",
        "Nürburgring GP-Strecke (Germany)",
        "Nürburgring Nordschleife (Germany)",
        "Autodromo Internazionale del Mugello (Italy)",
        "Autodromo dell’Umbria – Magione (Italy)",
        "Autodromo Piero Taruffi – Vallelunga (Italy)",
        "Trento-Bondone Hill Climb (Italy)",
        "WeatherTech Raceway Laguna Seca (USA)",
        "Brands Hatch Circuit (UK)",
        "Circuit Paul Ricard (France)"
    )


// --- VEHICLE OPTIONS BASED ON GAME ---
    val vehicleOptions = mapOf(
        "F1 24" to listOf(
            "Red Bull RB20",
            "Mercedes W15",
            "Ferrari SF-24",
            "McLaren MCL38",
            "Aston Martin AMR24",
            "Williams FW46",
            "RB VCARB 01",
            "Haas VF-24",
            "Sauber C44",
            "Alpine A524"
        ),
        "Assetto Corsa" to listOf(
            "Alpine A424",
            "Aston Martin V8 Vantage GT3",
            "Aston Martin Valkyrie AMR-LMH",
            "Audi R8 LMS Evo II GT3",
            "Audi R8 LMS GT2",
            "BMW M Hybrid V8",
            "BMW M4 GT3",
            "Bentley Continental GT3",
            "Cadillac V-Series.R",
            "Ferrari 296 GT3",
            "Ferrari 488 Challenge Evo",
            "Ferrari 488 GT3",
            "Ferrari 499P Hypercar",
            "Formula Hybrid 2023",
            "Honda NSX GT3 Evo",
            "Isotta Fraschini Tipo 6 LMH-C",
            "KTM X-Bow GT2",
            "Lamborghini Huracán GT3",
            "Lamborghini Huracán GT3 Evo2",
            "Lamborghini SC63",
            "Lamborghini Super Trofeo Huracán",
            "Lexus RC F GT3",
            "Mazda MX-5 Cup",
            "Maserati MC20 GT2",
            "McLaren 650S GT3",
            "McLaren 720S GT3 Evo",
            "Mercedes-AMG GT3 Evo",
            "Nissan GTR R35",
            "Nissan GT-R Nismo GT3",
            "Peugeot 9X8 Hypercar",
            "Porsche 911 GT2 RS Clubsport Evo",
            "Porsche 911 GT3 Cup (992)",
            "Porsche 911 GT3 R (992)",
            "Porsche 963",
            "Toyota GR010 Hybrid",
            "Toyota Supra"
        )
    )

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

            ExposedDropdownMenuBox(
                expanded = expandedGame,
                onExpandedChange = { expandedGame = !expandedGame }
            ) {
                OutlinedTextField(
                    value = selectedGame,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Game") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGame) },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )

                ExposedDropdownMenu(
                    expanded = expandedGame,
                    onDismissRequest = { expandedGame = false }
                ) {
                    gameOptions.forEach { game ->
                        DropdownMenuItem(
                            text = { Text(game) },
                            onClick = {
                                selectedGame = game
                                expandedGame = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expandedTrack,
                onExpandedChange = { expandedTrack = !expandedTrack }
            ) {
                OutlinedTextField(
                    value = selectedTrack,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Track") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTrack) },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )

                ExposedDropdownMenu(
                    expanded = expandedTrack,
                    onDismissRequest = { expandedTrack = false }
                ) {
                    trackOptions.forEach { track ->
                        DropdownMenuItem(
                            text = { Text(track) },
                            onClick = {
                                selectedTrack = track
                                expandedTrack = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expandedVehicle,
                onExpandedChange = { expandedVehicle = !expandedVehicle }
            ) {
                OutlinedTextField(
                    value = selectedVehicle,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Vehicle") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedVehicle) },
                    enabled = selectedGame.isNotEmpty(),
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )

                val vehicles = vehicleOptions[selectedGame] ?: emptyList()

                ExposedDropdownMenu(
                    expanded = expandedVehicle,
                    onDismissRequest = { expandedVehicle = false }
                ) {
                    vehicles.forEach { vehicle ->
                        DropdownMenuItem(
                            text = { Text(vehicle) },
                            onClick = {
                                selectedVehicle = vehicle
                                expandedVehicle = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Save button
            Button(
                onClick = {
                    val lapTime = "${minutes}:${seconds}:${millis}"
                    onSaveLap(name, selectedGame, selectedTrack, selectedVehicle, lapTime)
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
