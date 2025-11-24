package com.example.lap_time_tracker.ui.theme.laplist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lap_time_tracker.data.LapTimeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LapListScreen(
    viewModel: LapListViewModel,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLapSelected: (LapTimeEntity) -> Unit
) {
    val lapList = viewModel.allLaps.collectAsState()

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
                .padding(padding)
                .background(Color(0xFFD9D9D9))
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // List of laps
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

                items(lapList.value.size) { index ->
                    val lap = lapList.value[index]

                    LapCard(
                        lap = lap,
                        onClick = { onLapSelected(lap) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun LapCard(lap: LapTimeEntity, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC3C3C3), RoundedCornerShape(16.dp))
            .padding(20.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = lap.lapTime,
            fontSize = 28.sp,
            color = Color(0xFF2962FF)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(lap.name)
        Text(lap.game)
        Text(lap.track)
        Text(lap.vehicle)
    }
}