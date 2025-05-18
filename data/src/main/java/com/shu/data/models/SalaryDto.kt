package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.SalaryDbo


data class SalaryDao (

  @SerializedName("full" ) var full : String? = null

)

fun SalaryDao.toDb() : SalaryDbo {
  return SalaryDbo(
    full = this.full ?: "no",
  )
}