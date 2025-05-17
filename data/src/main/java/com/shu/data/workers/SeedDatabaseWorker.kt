package com.shu.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.shu.data.db.AppDatabase
import com.shu.data.models.SearchDao
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
                        /* val offerType = object : TypeToken<List<OfferDbo>>() {}.type
                         val vacancyType = object : TypeToken<List<VacancyDbo>>() {}.type*/
                        val searchType = object : TypeToken<SearchDao>() {}.type


                        /* val offers: List<OfferDbo> = Gson().fromJson(jsonReader, offerType)
                         val vacancy: List<VacancyDbo> = Gson().fromJson(jsonReader, vacancyType)*/
                        val search: SearchDao = Gson().fromJson(jsonReader, searchType)

                        val database = AppDatabase.getInstance(applicationContext)
                        Log.i(TAG, " search.offers = ${search.offers.size})")
                        database.offersDao().insertAll(search.offers)
                        database.vacanciesDao().insertAll(search.vacancies)
                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "DATA_FILENAME"
    }
}
