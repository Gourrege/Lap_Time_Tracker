package com.example.lap_time_tracker.ui.theme.laplist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lap_time_tracker.data.LapDatabase
import com.example.lap_time_tracker.data.LapRepository
import com.example.lap_time_tracker.data.LapTimeEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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

    fun getLapById(id: Int): LapTimeEntity? = allLaps.value.firstOrNull { it.id == id }

    fun deleteLap(lap: LapTimeEntity) {
        viewModelScope.launch {
            repository.deleteLap(lap)
        }
    }

    fun updateLap(lap: LapTimeEntity) {
        viewModelScope.launch {
            repository.updateLap(lap)
        }
    }
}