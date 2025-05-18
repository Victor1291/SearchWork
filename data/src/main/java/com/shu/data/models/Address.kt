package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.AddressDbo
import com.shu.data.db.models.OfferDbo


data class Address (

  @SerializedName("town"   ) var town   : String? = null,
  @SerializedName("street" ) var street : String? = null,
  @SerializedName("house"  ) var house  : String? = null

)

fun Address.toDb() : AddressDbo {
  return AddressDbo(
    town = this.town ?: "no",
    street = this.street ?: "no",
    house = this.house ?: "no"
  )
}