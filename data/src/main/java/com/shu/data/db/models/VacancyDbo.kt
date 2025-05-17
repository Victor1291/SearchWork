package com.shu.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancies")
data class VacancyDbo (
    @PrimaryKey
    @ColumnInfo("id"               ) var id               : String?           = null,
    @ColumnInfo("lookingNumber"    ) var lookingNumber    : Int?              = null,
    @ColumnInfo("title"            ) var title            : String?           = null,
    @Embedded("address")
    @ColumnInfo("address"          ) var address          : AddressDbo?          = AddressDbo(),
    @ColumnInfo("company"          ) var company          : String?           = null,
    @Embedded("exp")
    @ColumnInfo("experience"       ) var experience       : ExperienceDbo?       = ExperienceDbo(),
    @ColumnInfo("publishedDate"    ) var publishedDate    : String?           = null,
    @ColumnInfo("isFavorite"       ) var isFavorite       : Boolean?          = null,
    @Embedded("sal")
    @ColumnInfo("salary"           ) var salary           : SalaryDbo?           = SalaryDbo(),
    @ColumnInfo("schedules"        ) var schedules        : ArrayList<String> = arrayListOf(),
    @ColumnInfo("appliedNumber"    ) var appliedNumber    : Int?              = null,
    @ColumnInfo("description"      ) var description      : String?           = null,
    @ColumnInfo("responsibilities" ) var responsibilities : String?           = null,
    @ColumnInfo("questions"        ) var questions        : ArrayList<String> = arrayListOf()

)