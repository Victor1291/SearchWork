package com.shu.entity.models

data class Offers(
    override val hasId: String = "offers",
    val offers: List<Offer>
): HasStringId
