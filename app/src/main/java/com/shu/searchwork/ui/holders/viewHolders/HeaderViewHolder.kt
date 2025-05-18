package com.shu.searchwork.ui.holders.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.entity.models.Offers
import com.shu.searchwork.R
import com.shu.searchwork.databinding.CardItemBinding
import com.shu.searchwork.databinding.HeaderItemBinding
import com.shu.searchwork.ui.adapter.OfferAdapter
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ViewHolderVisitor

class HeaderViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.header_item

    override fun acceptBinding(item: Any): Boolean = item is RecyclerHeader

    override fun bind(
        binding: ViewBinding,
        item: Any,
        clickListener: AdapterClickListenerById,
        position: Int
    ) {
        with((binding as HeaderItemBinding)) {
            with(item as RecyclerHeader) {
                binding.header.text = item.text
            }
        }
    }
}