package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.AddressDbo
import com.shu.data.db.models.ExperienceDbo
import com.shu.data.db.models.OfferDbo
import com.shu.data.db.models.SalaryDbo
import com.shu.data.db.models.VacancyDbo


data class Vacancies(

    @SerializedName("id") var id: String? = null,
    @SerializedName("lookingNumber") var lookingNumber: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("company") var company: String? = null,
    @SerializedName("experience") var experience: Experience? = Experience(),
    @SerializedName("publishedDate") var publishedDate: String? = null,
    @SerializedName("isFavorite") var isFavorite: Boolean? = null,
    @SerializedName("salary") var salary: Salary? = Salary(),
    @SerializedName("schedules") var schedules: List<String>? = listOf(),
    @SerializedName("appliedNumber") var appliedNumber: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("responsibilities") var responsibilities: String? = null,
    @SerializedName("questions") var questions: List<String>? = listOf()

)

fun Vacancies.toDb(): VacancyDbo {
    return VacancyDbo(
        id = this.id ?: "1",
        lookingNumber = this.lookingNumber ?: 1,
        title = this.title ?: "no title",
        address = this.address?.toDb() ?: AddressDbo("no","no","no"),
        company = this.company ?: "no title",
        experience = this.experience?.toDb() ?: ExperienceDbo(
            previewText = "no",
            text = "no"
        ),
        publishedDate = this.publishedDate ?: "no title",
        isFavorite = this.isFavorite ?: false,
        salary = this.salary?.toDb() ?: SalaryDbo("no"),
        schedules = this.schedules ?: emptyList(),
        appliedNumber = this.appliedNumber ?: 0,
        description = this.description ?: "no title",
        responsibilities = this.responsibilities ?: "no title",
        questions = this.questions ?: emptyList(),
    )
}