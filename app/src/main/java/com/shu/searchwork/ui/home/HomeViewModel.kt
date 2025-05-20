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
import com.shu.searchwork.ui.holders.viewHolders.TwoHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    var list: List<HasStringId> = emptyList(),
    var isArrow: Boolean = false,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    getOffersUseCase: GetOffersUseCase,
    getVacanciesUseCase: GetVacanciesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : ViewModel() {

    private val offers: Flow<List<Offer>> = getOffersUseCase.invoke()

    private val vacancies: Flow<List<Vacancy>> = getVacanciesUseCase.invoke()

    val isClickButton = MutableStateFlow(false)

    val stateUi = combine(offers, vacancies, isClickButton) { offers, vacancies, isClick ->
        val list: MutableList<HasStringId> = mutableListOf()


        val size = vacancies.size
        if (size > 3 && !isClick) {
            if (offers.isNotEmpty()) {
                list.add(Offers(offers = offers))
            }

            list.add(RecyclerHeader(text = "Вакансии для вас"))
            repeat(3) {
                list.add(vacancies[it])
            }
            list.add(
                BottomButton(
                    text = "Ещё ${countToString(size - 3)}"
                )
            )
        } else {
            list.add(TwoHeader(text = countToString(size), textTwo = "По соответствию "))
            vacancies.forEach { vacancy ->
                list.add(vacancy)
            }
        }
        UiState(
            list = list,
            isArrow = isClick
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = UiState()
    )

    fun clickButton() {
        isClickButton.value = !isClickButton.value
    }

    fun updateFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteUseCase.invoke(id, isFavorite)
        }
    }

    fun countToString(count: Int): String {
        return when (count) {
            0 -> ""
            1, 21, 31, 41, 51 -> "$count вакансия"
            2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

}