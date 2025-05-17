package com.shu.searchwork.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shu.entity.models.Offer
import com.shu.searchwork.databinding.ItemOfferBinding

class OfferAdapter(
    private val onClickImage: (Offer) -> Unit,
) : ListAdapter<Offer, OfferViewHolder>(ItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = ItemOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OfferViewHolder(binding, onClickImage)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)
        holder.bind(offer)
    }

}

class OfferViewHolder(
    private val binding: ItemOfferBinding,
    private val onClickImage: (Offer) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var item: Offer

    init {
        binding.materialCardView.setOnClickListener {
            onClickImage(item)
        }
    }

    fun bind(item: Offer) {
        this.item = item

        binding.title.text = item.title

    }

}

class ItemDiffCallback : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

}