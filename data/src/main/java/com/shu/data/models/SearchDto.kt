package com.shu.data.models


data class SearchDto(
    val offers: List<OffersDto>,
    val vacancies: List<VacanciesDto>
)