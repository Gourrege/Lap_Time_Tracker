@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lap_time_tracker.ui.theme.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lap_time_tracker.ui.theme.components.AppTopBar


@Composable
fun DashboardScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { AppTopBar(navController) },
        containerColor = Color(0xFFD9D9D9)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DashboardButton(
                modifier = Modifier.fillMaxWidth(0.7f),
                icon = Icons.Default.Add,
                label = "Add Lap",
                onClick = {navController.navigate("addLap")}
            )

            Spacer(Modifier.height(32.dp))

            DashboardButton(
                modifier = Modifier.fillMaxWidth(0.7f),
                icon = Icons.Default.Folder,
                label = "Lap List",
                onClick = {navController.navigate("lapList")}
            )
        }
    }
}

@Composable
private fun DashboardButton(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5A526F),
            contentColor = Color.White
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(6.dp))
            Text(label, fontSize = 14.sp)
        }
    }
}
