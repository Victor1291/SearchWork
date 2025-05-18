package com.shu.searchwork.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.shu.entity.models.HasStringId
import com.shu.searchwork.R
import com.shu.searchwork.databinding.ButtonItemBinding
import com.shu.searchwork.databinding.CardItemBinding
import com.shu.searchwork.databinding.HeaderItemBinding
import com.shu.searchwork.databinding.HeaderTwoItemBinding
import com.shu.searchwork.databinding.ItemVacancyBinding
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ViewHolderVisitor
import com.shu.searchwork.ui.holders.ViewHoldersManager

class GalleryAdapter(
    private val viewHoldersManager: ViewHoldersManager,
    private val clickListener: AdapterClickListenerById,
    // private val onClick: (MediaStoreImage) -> Unit
) : ListAdapter<HasStringId, GalleryAdapter.DataViewHolder>(BaseDiffCallback()) {

    inner class DataViewHolder(
        private val binding: ViewBinding,
        private val holder: ViewHolderVisitor,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HasStringId,clickListener: AdapterClickListenerById, position: Int) =
            holder.bind(binding, item, clickListener, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val holder = viewHoldersManager.getViewHolder(viewType)
        val view = when (holder.layout) {

            R.layout.card_item -> {
                CardItemBinding.inflate(layoutInflater, parent, false)
            }

            R.layout.item_vacancy -> {
                ItemVacancyBinding.inflate(layoutInflater, parent, false)
            }

            R.layout.button_item -> {
                ButtonItemBinding.inflate(layoutInflater, parent, false)
            }

            R.layout.header_item -> {
                HeaderItemBinding.inflate(layoutInflater, parent, false)
            }
            R.layout.header_two_item -> {
                HeaderTwoItemBinding.inflate(layoutInflater, parent, false)
            }

            else -> {
                throw IllegalArgumentException("Wrong type")
            }
        }

        return DataViewHolder(view, holder)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(getItem(position),clickListener,  position)

    override fun getItemViewType(position: Int): Int =
        viewHoldersManager.getItemType(getItem(position))

}

class BaseDiffCallback : DiffUtil.ItemCallback<HasStringId>() {
    override fun areItemsTheSame(oldItem: HasStringId, newItem: HasStringId): Boolean =
        oldItem.hasId == newItem.hasId

    override fun areContentsTheSame(oldItem: HasStringId, newItem: HasStringId): Boolean =
        oldItem == newItem
}


