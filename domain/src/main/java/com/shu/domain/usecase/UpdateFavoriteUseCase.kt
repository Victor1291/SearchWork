package com.shu.domain.usecase

import com.shu.entity.repository.Repository
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(id: String, isFavorite: Boolean){
        repository.updateFavorite(id,isFavorite)
    }

}