package com.shu.searchwork.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.usecase.GetOffersUseCase
import com.shu.domain.usecase.GetVacanciesUseCase
import com.shu.entity.models.HasStringId
import com.shu.entity.models.Offer
import com.shu.entity.models.Offers
import com.shu.entity.models.Vacancies
import com.shu.entity.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getOffersUseCase: GetOffersUseCase,
    getVacanciesUseCase: GetVacanciesUseCase,
) : ViewModel() {

    val offers: Flow<List<Offer>> = getOffersUseCase.invoke()

    val vacancies: Flow<List<Vacancy>> = getVacanciesUseCase.invoke()

    val stateUi = combine(offers, vacancies) { offers, vacancies ->
        val list : MutableList<HasStringId> = mutableListOf()
        list.add(Offers(offers = offers))
        vacancies.forEach {vacancy ->
           list.add(vacancy)
        }
        list
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

}