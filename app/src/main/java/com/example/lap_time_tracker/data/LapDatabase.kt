package com.example.lap_time_tracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LapTimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LapDatabase : RoomDatabase() {

    abstract fun lapTimeDao(): LapTimeDao

    companion object {
        @Volatile
        private var INSTANCE: LapDatabase? = null

        fun getDatabase(context: Context): LapDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LapDatabase::class.java,
                    "lap_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
