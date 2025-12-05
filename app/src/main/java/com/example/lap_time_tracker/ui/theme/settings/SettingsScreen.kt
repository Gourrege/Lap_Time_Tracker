@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lap_time_tracker.ui.theme.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lap_time_tracker.ui.theme.components.AppTopBar

@Composable
fun SettingsScreen(
    navController: NavController,
    onDeleteAllLaps: (() -> Unit)? = null
) {
    var isDarkMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppTopBar(navController) },
        containerColor = Color(0xFFD9D9D9)
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Settings",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Theme Toggle ---
            SettingCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                        contentDescription = "Theme",
                        tint = Color.Black
                    )
                    Spacer(Modifier.width(16.dp))
                    Text("Dark Mode", fontSize = 18.sp)
                    Spacer(Modifier.weight(1f))

                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { isDarkMode = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Delete All Laps ---
            SettingCard(
                modifier = Modifier.clickable {
                    onDeleteAllLaps?.invoke()
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "", tint = Color.Red)
                    Spacer(Modifier.width(16.dp))
                    Text("Delete All Laps", color = Color.Red, fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- App Info ---
            SettingCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = "", tint = Color.Black)
                    Spacer(Modifier.width(16.dp))
                    Text("App Info", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- About ---
            SettingCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = "", tint = Color.Black)
                    Spacer(Modifier.width(16.dp))
                    Text("About Developer", fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun SettingCard(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFBDBDBD), RoundedCornerShape(12.dp))
            .padding(16.dp),
        content = content
    )
}


