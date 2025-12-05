package com.example.lap_time_tracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lap_times")
data class LapTimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val game: String,
    val track: String,
    val vehicle: String,
    val lapTime: String,      // format: "1:24:34"
    val imageUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)