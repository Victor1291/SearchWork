package com.shu.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.shu.data.db.AppDatabase
import com.shu.data.models.SearchDto
import com.shu.data.models.toDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val searchType = object : TypeToken<SearchDto>() {}.type
                        val search: SearchDto = Gson().fromJson(jsonReader, searchType)

                        val database = AppDatabase.getInstance(applicationContext)
                        Log.i(TAG, " search.offers = ${search.offers.size} :)")
                        Log.i(TAG, " search.vacancies = ${search.vacancies.size} :)")
                        database.offersDao().insertAll(search.offers.map { offer ->
                            offer.toDb()
                        })
                        database.vacanciesDao().insertAll(search.vacancies.map { vacancy ->
                            vacancy.toDb()
                        })
                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
           /* val filename = inputData.getString(KEY_FILENAME2)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val plantType = object : TypeToken<List<Plant>>() {}.type
                        val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)

                        val database = AppDatabase.getInstance(applicationContext)
                        database.plantDao().insertAll(plantList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }*/
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "DATA_FILENAME"
        const val KEY_FILENAME2 = "DATA_FILENAME2"
    }
}
