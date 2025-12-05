package com.example.lap_time_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.lap_time_tracker.ui.theme.navigation.AppNavHost
import com.example.lap_time_tracker.ui.theme.Lap_Time_TrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Lap_Time_TrackerTheme {
                LapTimeApp()

            }
        }
    }
}

@Preview
@Composable
fun LapTimeApp()
{
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        AppNavHost(navController = navController)
    }


}
