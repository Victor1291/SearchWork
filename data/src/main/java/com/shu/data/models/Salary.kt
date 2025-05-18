package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.OfferDbo
import com.shu.data.db.models.SalaryDbo


data class Salary (

  @SerializedName("full" ) var full : String? = null

)

fun Salary.toDb() : SalaryDbo {
  return SalaryDbo(
    full = this.full ?: "no",
  )
}