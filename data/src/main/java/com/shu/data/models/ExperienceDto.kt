package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.ExperienceDbo


data class ExperienceDto (

  @SerializedName("previewText" ) var previewText : String? = null,
  @SerializedName("text"        ) var text        : String? = null

)

fun ExperienceDto.toDb() : ExperienceDbo {
  return ExperienceDbo(
    previewText = this.previewText ?: "no",
    text = this.text ?: "no",
  )
}