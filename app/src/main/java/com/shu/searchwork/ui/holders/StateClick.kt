package com.shu.searchwork.ui.holders

data class StateClick(
    var id: String = "card",
    var position: Int = 0,
    var itemTypes: Int = ItemTypes.CARD,
    val isFavorite: Boolean = false,
    val favorite: Boolean = false,
    val itemId: String = ""
)
