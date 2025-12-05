package com.example.lap_time_tracker.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.lap_time_tracker.data.LapDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class CleanupWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        Log.d("CleanupWorker", "⚙️ Worker started")

        return try {
            val deletedCount = withContext(Dispatchers.IO) {
                val dao = LapDatabase.getDatabase(applicationContext).lapTimeDao()

                val cutoff = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(30)
                val count = dao.deleteOlderThan(cutoff)

                Log.d("CleanupWorker", "Deleted $count laps older than 30 days")
                count
            }

            Log.d("CleanupWorker", "Worker finished successfully (deleted $deletedCount items)")
            Result.success()

        } catch (e: Exception) {
            Log.e("CleanupWorker", "Worker FAILED: ${e.message}")
            e.printStackTrace()
            Result.retry()
        }
    }
}
