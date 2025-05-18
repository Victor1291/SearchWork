package com.shu.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.models.Address
import com.shu.entity.models.Salary
import com.shu.entity.models.Vacancy

@Entity(tableName = "vacancies")
data class VacancyDbo(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("lookingNumber") val lookingNumber: Int,
    @ColumnInfo("title") val title: String,
    @Embedded("address") val address: AddressDbo,
    @ColumnInfo("company") val company: String,
    @Embedded("experience") val experience: ExperienceDbo,
    @ColumnInfo("publishedDate") val publishedDate: String,
    @ColumnInfo("isFavorite") val isFavorite: Boolean,
    @Embedded("salary") val salary: SalaryDbo,
    @ColumnInfo("schedules") val schedules: List<String>,
    @ColumnInfo("appliedNumber") val appliedNumber: Int,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("responsibilities") val responsibilities: String,
    @ColumnInfo("questions") val questions: List<String>
)

fun VacancyDbo.fromDb(): Vacancy {
    return Vacancy(
        id = this.id ?: "1",
        lookingNumber = this.lookingNumber ?: 1,
        title = this.title ?: "no title",
        address = this.address.fromDb() ?: Address("no", "no", "no"),
        company = this.company ?: "no title",
        experience = this.experience.fromDb(),
        publishedDate = this.publishedDate ?: "no title",
        isFavorite = this.isFavorite ?: false,
        salary = this.salary.fromDb() ?: Salary("no"),
        schedules = this.schedules ?: emptyList(),
        appliedNumber = this.appliedNumber ?: 0,
        description = this.description ?: "no title",
        responsibilities = this.responsibilities ?: "no title",
        questions = this.questions ?: emptyList(),
    )
}