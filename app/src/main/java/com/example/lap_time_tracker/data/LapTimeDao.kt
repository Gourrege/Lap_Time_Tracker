package com.example.lap_time_tracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LapTimeDao {

    @Insert
    suspend fun insertLap(lap: LapTimeEntity)

    @Query("SELECT * FROM lap_times ORDER BY timestamp DESC")
    fun getAllLaps(): Flow<List<LapTimeEntity>>

    @Query("SELECT * FROM lap_times WHERE id = :id")
    suspend fun getLap(id: Int): LapTimeEntity?
}