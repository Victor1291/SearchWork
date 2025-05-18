package com.shu.searchwork.ui.holders.viewHolders

import com.shu.entity.models.HasStringId


data class TwoHeader(
    override val hasId: String = "headerTwo",
    val text: String,
    val textTwo: String
) : HasStringId
