package com.shu.data.db.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.shu.entity.models.Address
import com.shu.entity.models.Experience


data class ExperienceDbo (

  @ColumnInfo("previewText" ) var previewText : String? = null,
  @ColumnInfo("text"        ) var text        : String? = null

)

fun ExperienceDbo.fromDb(): Experience {
  return Experience(
    previewText = this.previewText ?: "no",
    text = this.text ?: "no",
  )
}