package com.shu.searchwork.ui.adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Icon
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton.IconGravity
import com.shu.entity.models.Offer
import com.shu.searchwork.R
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

        when (item.id) {
            "near_vacancies" -> {
                binding.image.setBackgroundResource(R.drawable.find)
                binding.imageColor.setBackgroundResource(R.drawable.circle_blue)
                binding.button.visibility= View.GONE
            }
            "level_up_resume" -> {
                binding.image.setBackgroundResource(R.drawable.star_1)
                binding.imageColor.setBackgroundResource(R.drawable.circle)
                binding.button.visibility= View.VISIBLE
            }
            "temporary_job" -> {
                binding.image.setBackgroundResource(R.drawable.task)
                binding.imageColor.setBackgroundResource(R.drawable.circle)
                binding.button.visibility= View.GONE
            }
        }

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