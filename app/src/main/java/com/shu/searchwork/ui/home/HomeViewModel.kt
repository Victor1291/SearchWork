package com.shu.searchwork.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.usecase.GetOffersUseCase
import com.shu.entity.models.Offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    val offers: Flow<List<Offer>> = getOffersUseCase.invoke().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

}