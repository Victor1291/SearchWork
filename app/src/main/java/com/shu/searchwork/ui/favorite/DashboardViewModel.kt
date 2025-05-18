package com.shu.searchwork.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.usecase.GetVacanciesUseCase
import com.shu.domain.usecase.UpdateFavoriteUseCase
import com.shu.entity.models.HasStringId
import com.shu.entity.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getVacanciesUseCase: GetVacanciesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : ViewModel() {
    private val vacancies: Flow<List<Vacancy>> = getVacanciesUseCase.invoke()

    private val isClickButton = MutableStateFlow(false)

    val stateUi = combine(vacancies, isClickButton) { vacancies, isClick ->
        val list: MutableList<HasStringId> = mutableListOf()
        vacancies.forEach { vacancy ->
            if (vacancy.isFavorite) {
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