package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.AddressDbo


data class AddressDao (

  @SerializedName("town"   ) var town   : String? = null,
  @SerializedName("street" ) var street : String? = null,
  @SerializedName("house"  ) var house  : String? = null

)

fun AddressDao.toDb() : AddressDbo {
  return AddressDbo(
    town = this.town ?: "no",
    street = this.street ?: "no",
    house = this.house ?: "no"
  )
}