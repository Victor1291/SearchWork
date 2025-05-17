package com.shu.data.models

import com.google.gson.annotations.SerializedName


data class SearchDao (

  @SerializedName("offers"    ) var offers    : ArrayList<Offer>    = arrayListOf(),
  @SerializedName("vacancies" ) var vacancies : ArrayList<Vacancies> = arrayListOf()

)