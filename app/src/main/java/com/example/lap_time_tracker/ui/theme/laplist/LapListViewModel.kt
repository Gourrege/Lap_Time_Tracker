package com.example.lap_time_tracker.ui.theme.laplist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lap_time_tracker.data.LapDatabase
import com.example.lap_time_tracker.data.LapRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LapListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LapRepository

    val allLaps = LapDatabase.getDatabase(application)
        .lapTimeDao()
        .getAllLaps()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        val db = LapDatabase.getDatabase(application)
        repository = LapRepository(db.lapTimeDao())
    }
}