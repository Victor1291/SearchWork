package com.shu.data.models

import com.google.gson.annotations.SerializedName


data class Experience (

  @SerializedName("previewText" ) var previewText : String? = null,
  @SerializedName("text"        ) var text        : String? = null

)