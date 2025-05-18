package com.shu.domain.usecase

import com.shu.entity.models.Vacancy
import com.shu.entity.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVacanciesUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<List<Vacancy>> {
        return repository.getVacancies()
    }

}