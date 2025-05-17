package com.shu.data.models

import com.google.gson.annotations.SerializedName
import com.shu.data.db.models.OfferDbo
import com.shu.data.db.models.VacancyDbo


data class SearchDao (

  @SerializedName("offers"    ) var offers    : ArrayList<OfferDbo>    = arrayListOf(),
  @SerializedName("vacancies" ) var vacancies : ArrayList<VacancyDbo> = arrayListOf()

)