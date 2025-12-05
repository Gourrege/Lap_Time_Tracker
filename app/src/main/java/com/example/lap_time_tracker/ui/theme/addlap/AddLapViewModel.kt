package com.example.lap_time_tracker.ui.theme.addlap

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lap_time_tracker.data.LapDatabase
import com.example.lap_time_tracker.data.LapRepository
import com.example.lap_time_tracker.data.LapTimeEntity
import kotlinx.coroutines.launch

class AddLapViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LapRepository

    init {
        val db = LapDatabase.getDatabase(application)
        repository = LapRepository(db.lapTimeDao())
    }

    fun saveLap(
        name: String,
        game: String,
        track: String,
        vehicle: String,
        lapTime: String,
        imageUri: String?
    ) {
        viewModelScope.launch {
            val lap = LapTimeEntity(
                name = name,
                game = game,
                track = track,
                vehicle = vehicle,
                lapTime = lapTime,
                imageUri = imageUri
            )
            repository.insertLap(lap)
        }
    }
}