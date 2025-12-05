@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lap_time_tracker.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lap_time_tracker.R

@Composable
fun AppTopBar(navController: NavController) {

    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp) // adjust the height if needed
            )

        },

        navigationIcon = {
            IconButton(onClick = { menuExpanded = true }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Dashboard") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("dashboard")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Add a lap") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("addLap")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Lap list") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("lapList")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("settings")
                    }
                )
            }
        },

        actions = {
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }

        }
    )
}
