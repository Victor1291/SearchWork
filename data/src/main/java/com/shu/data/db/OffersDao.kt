package com.shu.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shu.data.db.models.OfferDbo
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Offer class.
 */
@Dao
interface OffersDao {
    @Query("SELECT * FROM offers ")
    fun getOffers(): Flow<List<OfferDbo>>

    @Query("SELECT * FROM offers WHERE id = :plantId")
    fun getOffer(plantId: String): Flow<OfferDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<OfferDbo>)
}
