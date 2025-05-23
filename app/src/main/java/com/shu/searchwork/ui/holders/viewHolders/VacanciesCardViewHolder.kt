package com.shu.searchwork.ui.holders.viewHolders

import android.text.format.DateUtils
import android.util.Log
import androidx.viewbinding.ViewBinding
import com.shu.entity.models.Vacancy
import com.shu.searchwork.R
import com.shu.searchwork.databinding.ItemVacancyBinding
import com.shu.searchwork.ui.holders.AdapterClickListenerById
import com.shu.searchwork.ui.holders.ItemTypes
import com.shu.searchwork.ui.holders.StateClick
import com.shu.searchwork.ui.holders.ViewHolderVisitor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit


class VacanciesCardViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.item_vacancy

    override fun acceptBinding(item: Any): Boolean = item is Vacancy

    override fun bind(
        binding: ViewBinding,
        item: Any,
        clickListener: AdapterClickListenerById,
        position: Int
    ) {
        with((binding as ItemVacancyBinding)) {
            with(item as Vacancy) {
                binding.title.text = item.title
                binding.lookingNumber.text = "Сейчас просматривает ${item.lookingNumber} человек"
                binding.salary.text = item.salary.full
                binding.address.text = item.address.town
                binding.company.text = item.company
                binding.experience.text = item.experience.text


                binding.publishedDate.text = "Опубликовано ${item.published}"

                binding.favorite.isSelected = item.isFavorite
                binding.favorite.setOnClickListener {
                    binding.favorite.isSelected = !item.isFavorite
                    clickListener.onClick(
                        StateClick(
                            id = item.hasId,
                            position = position,
                            itemTypes = ItemTypes.VACANCY,
                            isFavorite = true,
                            favorite = !item.isFavorite,
                            itemId = item.id
                        )
                    )
                }

                binding.materialCardView.setOnClickListener {
                    Log.d("click", "click on Button $position , ${item.hasId}")
                    clickListener.onClick(
                        StateClick(
                            id = item.hasId,
                            position = position,
                            itemTypes = ItemTypes.VACANCY,
                            isFavorite = false
                        )
                    )
                }

            }
        }

    }
}