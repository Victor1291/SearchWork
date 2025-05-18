package com.shu.entity.models

data class Vacancy(
     override val hasId: String = "vacancy",
     var id: String,
     var lookingNumber: Int,
     var title: String,
     var address: Address,
     var company: String,
     var experience: Experience,
     var publishedDate: String,
     var isFavorite: Boolean,
     var salary: Salary,
     var schedules: List<String>,
     var appliedNumber: Int,
     var description: String,
     var responsibilities: String,
     var questions: List<String>
): HasStringId
