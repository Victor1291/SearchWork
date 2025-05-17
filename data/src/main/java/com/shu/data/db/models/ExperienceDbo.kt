package com.shu.data.db.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class ExperienceDbo (

  @ColumnInfo("previewText" ) var previewText : String? = null,
  @ColumnInfo("text"        ) var text        : String? = null

)