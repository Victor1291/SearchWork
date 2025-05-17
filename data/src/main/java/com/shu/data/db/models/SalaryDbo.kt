package com.shu.data.db.models

import androidx.room.ColumnInfo


data class SalaryDbo(

    @ColumnInfo("full") var full: String? = null

)