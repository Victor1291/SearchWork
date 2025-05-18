package com.shu.entity.repository

import com.shu.entity.models.Offer
import com.shu.entity.models.Vacancy
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getOffers(): Flow<List<Offer>>

    fun getVacancies(): Flow<List<Vacancy>>

    suspend fun updateFavorite(id: String, isFavorite: Boolean)

}