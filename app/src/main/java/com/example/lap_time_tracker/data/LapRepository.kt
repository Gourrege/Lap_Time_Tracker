package com.example.lap_time_tracker.data

class LapRepository(private val dao: LapTimeDao) {

    fun getAllLaps() = dao.getAllLaps()

    suspend fun insertLap(lap: LapTimeEntity) = dao.insertLap(lap)
}