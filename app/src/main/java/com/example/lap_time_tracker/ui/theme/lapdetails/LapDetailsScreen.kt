@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lap_time_tracker.ui.theme.lapdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lap_time_tracker.data.LapTimeEntity
import com.example.lap_time_tracker.ui.theme.components.AppTopBar

@Composable
fun LapDetailsScreen(
    navController: NavController,
    lap: LapTimeEntity?,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Scaffold(
        topBar = { AppTopBar(navController) },
        containerColor = Color(0xFFD9D9D9)
    ) { padding ->

        if (lap == null) {
            // So the screen is NOT blank if something goes wrong
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Lap not found")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Big blue time
            Text(
                text = lap.lapTime,          // e.g. "1 : 24 : 34"
                fontSize = 40.sp,
                color = Color(0xFF0000FF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp)
            )

            InfoBox(text = lap.name.ifBlank { "Name" })
            InfoBox(text = lap.game.ifBlank { "Game" })
            InfoBox(text = lap.vehicle.ifBlank { "Vehicle" })
            InfoBox(text = lap.track.ifBlank { "Track" })

            Spacer(Modifier.height(16.dp))

            // Image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFFBDBDBD), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Image")
            }

            Spacer(Modifier.weight(1f))

            // Bottom Edit / Delete buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    icon = Icons.Default.Edit,
                    label = "Edit",
                    modifier = Modifier.weight(1f),
                    onClick = onEdit
                )
                ActionButton(
                    icon = Icons.Default.Delete,
                    label = "Delete",
                    modifier = Modifier.weight(1f),
                    onClick = onDelete
                )
            }
        }
    }
}

@Composable
private fun InfoBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(vertical = 4.dp)
            .background(Color(0xFFBDBDBD), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
private fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5A526F),
            contentColor = Color.White
        )
    ) {
        Icon(icon, contentDescription = label)
        Spacer(Modifier.width(8.dp))
        Text(label, fontWeight = FontWeight.SemiBold)
    }
}
