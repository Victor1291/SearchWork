package com.shu.searchwork.ui.holders.viewHolders

import com.shu.entity.models.HasStringId


data class BottomButton(
    override val hasId: String = "button",
    val text: String,
) : HasStringId
