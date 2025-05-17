package com.shu.domain.usecase

import com.shu.entity.models.Offer
import com.shu.entity.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<List<Offer>> {
        return repository.getOffers()
    }

}