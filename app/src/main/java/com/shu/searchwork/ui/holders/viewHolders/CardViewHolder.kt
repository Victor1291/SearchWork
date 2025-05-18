package com.shu.searchwork.ui.holders.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.entity.models.Offers
import com.shu.searchwork.R
import com.shu.searchwork.databinding.CardItemBinding
import com.shu.searchwork.ui.adapter.OfferAdapter
import com.shu.searchwork.ui.holders.ViewHolderVisitor

class CardViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.card_item

    override fun acceptBinding(item: Any): Boolean = item is Offers

    override fun bind(
        binding: ViewBinding,
        item: Any,
        position: Int
    ) {
        with((binding as CardItemBinding)) {
            with(item as Offers) {
                val offerAdapter =
                    OfferAdapter { offer ->

                    }

                binding.recycler.also { view ->
                    view.adapter = offerAdapter
                }
                offerAdapter.submitList(offers)

            }
        }

    }
}