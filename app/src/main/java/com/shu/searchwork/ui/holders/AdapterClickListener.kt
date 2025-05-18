package com.shu.searchwork.ui.holders

class AdapterClickListenerById(val clickListener: (stateClick: StateClick) -> Unit) {
    fun onClick(stateClick: StateClick) = clickListener(stateClick)
}