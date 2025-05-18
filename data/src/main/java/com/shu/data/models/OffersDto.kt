package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.OfferDbo


data class OffersDto (

  @SerializedName("id"    ) var id    : String? = null,
  @SerializedName("title" ) var title : String? = null,
  @SerializedName("link"  ) var link  : String? = null

)

fun OffersDto.toDb() : OfferDbo {
  return OfferDbo(
    id = this.id ?: "1",
    title = this.title ?: "no title",
    link = this.link ?: "no link"
  )
}