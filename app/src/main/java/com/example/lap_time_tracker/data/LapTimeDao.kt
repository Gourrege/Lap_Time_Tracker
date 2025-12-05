package com.example.lap_time_tracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LapTimeDao {

    @Insert
    suspend fun insertLap(lap: LapTimeEntity)

    @Query("SELECT * FROM lap_times ORDER BY timestamp DESC")
    fun getAllLaps(): Flow<List<LapTimeEntity>>

    @Query("SELECT * FROM lap_times WHERE id = :id")
    suspend fun getLap(id: Int): LapTimeEntity?

    @Delete
    suspend fun deleteLap(lap: LapTimeEntity)

    @Update
    suspend fun updateLap(lap: LapTimeEntity)

    @Query("DELETE FROM lap_times WHERE timestamp < :cutoff")
    suspend fun deleteOlderThan(cutoff: Long): Int
}