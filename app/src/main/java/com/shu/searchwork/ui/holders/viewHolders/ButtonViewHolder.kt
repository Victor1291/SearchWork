package com.shu.searchwork.ui.holders.viewHolders

import android.util.Log
import androidx.viewbinding.ViewBinding
import com.shu.searchwork.R
import com.shu.searchwork.databinding.ButtonItemBinding
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ItemTypes
import com.shu.searchwork.ui.holders.StateClick
import com.shu.searchwork.ui.holders.ViewHolderVisitor

class ButtonViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.button_item

    override fun acceptBinding(item: Any): Boolean = item is BottomButton

    override fun bind(
        binding: ViewBinding,
        item: Any,
        clickListener: AdapterClickListenerById,
        position: Int
    ) {
        with((binding as ButtonItemBinding)) {
            with(item as BottomButton) {
                binding.button.text = item.text

                binding.button.setOnClickListener {
                    Log.d("click", "click on Button $position , ${item.hasId}")
                    clickListener.onClick(
                        StateClick(
                            id = item.hasId,
                            position = position,
                            itemTypes = ItemTypes.BUTTON,
                        )
                    )
                }
            }
        }
    }
}