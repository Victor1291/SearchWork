package com.shu.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.shu.data.data.Converters
import com.shu.data.data.GardenPlanting
import com.shu.data.data.GardenPlantingDao
import com.shu.data.data.Plant
import com.shu.data.data.PlantDao
import com.shu.data.data.StringConverters
import com.shu.data.db.models.OfferDbo
import com.shu.data.db.models.VacancyDbo
import com.shu.data.utilities.DATABASE_NAME
import com.shu.data.utilities.DATA_FILENAME
import com.shu.data.utilities.PLANT_DATA_FILENAME
import com.shu.data.workers.SeedDatabaseWorker
import com.shu.data.workers.SeedDatabaseWorker.Companion.KEY_FILENAME
import com.shu.data.workers.SeedDatabaseWorker.Companion.KEY_FILENAME2

/**
 * The Room database for this app
 */
@Database(entities = [OfferDbo::class, VacancyDbo::class, GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class, StringConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offersDao(): OffersDao
    abstract fun vacanciesDao(): VacanciesDao
    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to DATA_FILENAME))
                               // .setInputData(workDataOf(KEY_FILENAME2 to PLANT_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}
