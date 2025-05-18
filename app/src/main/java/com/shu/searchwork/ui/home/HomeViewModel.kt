package com.shu.searchwork.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.usecase.GetOffersUseCase
import com.shu.domain.usecase.GetVacanciesUseCase
import com.shu.domain.usecase.UpdateFavoriteUseCase
import com.shu.entity.models.HasStringId
import com.shu.entity.models.Offer
import com.shu.entity.models.Offers
import com.shu.entity.models.Vacancy
import com.shu.searchwork.ui.holders.viewHolders.BottomButton
import com.shu.searchwork.ui.holders.viewHolders.RecyclerHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getOffersUseCase: GetOffersUseCase,
    getVacanciesUseCase: GetVacanciesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : ViewModel() {

    private val offers: Flow<List<Offer>> = getOffersUseCase.invoke()

    private val vacancies: Flow<List<Vacancy>> = getVacanciesUseCase.invoke()

    private val isClickButton = MutableStateFlow(false)

    val stateUi = combine(offers, vacancies, isClickButton) { offers, vacancies, isClick ->
        val list: MutableList<HasStringId> = mutableListOf()
        if (offers.isNotEmpty()) {
            list.add(Offers(offers = offers))
        }
        list.add(RecyclerHeader(text = "Вакансии для вас"))
        val size = vacancies.size
        if (size > 3 && !isClick) {
            repeat(3) {
                list.add(vacancies[it])
            }
            list.add(
                BottomButton(
                    text = "Ещё ${size - 3} вакансий"
                )
            )
        } else {
            vacancies.forEach {vacancy ->
                list.add(vacancy)
            }
        }
        list
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun clickButton() {
        isClickButton.value = true
    }

    fun updateFavorite(id: String,isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteUseCase.invoke(id, isFavorite)
        }
    }

}