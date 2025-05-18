package com.shu.data.db.models

import androidx.room.ColumnInfo
import com.shu.entity.models.Experience
import com.shu.entity.models.Salary


data class SalaryDbo(

    @ColumnInfo("full") var full: String? = null

)

fun SalaryDbo.fromDb(): Salary {
    return Salary(
        full = this.full ?: "no",
    )
}