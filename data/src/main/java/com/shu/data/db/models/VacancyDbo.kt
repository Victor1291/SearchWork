package com.shu.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "vacancies")
data class VacancyDbo(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("lookingNumber") val lookingNumber: Int,
    @ColumnInfo("title") val title: String,
    @Embedded("address") val address: AddressDbo? = AddressDbo(),
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