package com.shu.searchwork.ui.holders

import androidx.navigation.fragment.FragmentNavigator

data class StateClick(
    var id: String = "card",
    var position: Int = 0,
    var itemTypes: Int = ItemTypes.CARD,
)
