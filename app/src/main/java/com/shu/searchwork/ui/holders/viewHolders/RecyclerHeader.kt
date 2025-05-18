package com.shu.searchwork.ui.holders.viewHolders

import com.shu.entity.models.HasStringId


data class RecyclerHeader(
    override val hasId: String = "header",
    val text: String
) : HasStringId
