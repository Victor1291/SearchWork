package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.ExperienceDbo
import com.shu.data.db.models.OfferDbo


data class Experience (

  @SerializedName("previewText" ) var previewText : String? = null,
  @SerializedName("text"        ) var text        : String? = null

)

fun Experience.toDb() : ExperienceDbo {
  return ExperienceDbo(
    previewText = this.previewText ?: "no",
    text = this.text ?: "no",
  )
}