package com.shu.data.repository

import com.shu.data.db.OffersDao
import com.shu.data.db.VacanciesDao
import com.shu.data.db.models.VacancyDbo
import com.shu.data.db.models.fromDb
import com.shu.entity.models.Offer
import com.shu.entity.models.Vacancy
import com.shu.entity.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val offersDao: OffersDao,
    private val vacancyDao: VacanciesDao,
) : Repository {
    override fun getOffers(): Flow<List<Offer>> {
        return offersDao.getOffers().map { offers ->
            offers.map { offer ->
                offer.fromDb()
            }
        }
    }

    override fun getVacancies(): Flow<List<Vacancy>> {
       return vacancyDao.getVacancies().map { vacansies ->
           vacansies.map { vacancy ->
               vacancy.fromDb()
           }
       }
    }
}



