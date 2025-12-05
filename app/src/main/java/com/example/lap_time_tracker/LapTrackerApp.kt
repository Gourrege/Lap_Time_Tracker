package com.example.lap_time_tracker

import android.app.Application
import android.util.Log
import androidx.work.*
import com.example.lap_time_tracker.worker.CleanupWorker
import java.util.concurrent.TimeUnit

class LapTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        scheduleCleanupWorker()
    }

    fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)   // <-- enables debug logging!
            .build()
    }

    private fun scheduleCleanupWorker() {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val request = PeriodicWorkRequestBuilder<CleanupWorker>(
            1, TimeUnit.DAYS        // runs once a day
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "cleanup_old_laps",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
