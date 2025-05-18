package com.shu.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shu.data.db.models.OfferDbo
import com.shu.data.db.models.VacancyDbo
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Vacancy class.
 */
@Dao
interface VacanciesDao {
    @Query("SELECT * FROM vacancies ")
    fun getVacancies(): Flow<List<VacancyDbo>>

    @Query("SELECT * FROM vacancies WHERE id = :id")
    fun getVacancy(id: String): Flow<VacancyDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<VacancyDbo>)

    @Query("UPDATE vacancies SET isFavorite = :selected WHERE id = :vacancyId")
    suspend fun updateFavorite(vacancyId: String, selected: Boolean)
}
