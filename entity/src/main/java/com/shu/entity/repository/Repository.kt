package com.shu.entity.repository

import com.shu.entity.models.Offer
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getOffers(): Flow<List<Offer>>

}