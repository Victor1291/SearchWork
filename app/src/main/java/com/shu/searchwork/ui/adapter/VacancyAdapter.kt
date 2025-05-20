package com.shu.searchwork.ui.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shu.entity.models.Vacancy
import com.shu.searchwork.databinding.ItemVacancyBinding
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class VacancyAdapter(
    private val onClickImage: (Vacancy) -> Unit,
) : ListAdapter<Vacancy, VacancyViewHolder>(VacancyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = ItemVacancyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VacancyViewHolder(binding, onClickImage)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val offer = getItem(position)
        holder.bind(offer)
    }

}

class VacancyViewHolder(
    private val binding: ItemVacancyBinding,
    private val onClickImage: (Vacancy) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var item: Vacancy

    init {
        binding.materialCardView.setOnClickListener {
            onClickImage(item)
        }
    }

    fun bind(item: Vacancy) {
        this.item = item

        binding.title.text = item.title
        binding.lookingNumber.text = "Сейчас просматривает ${item.lookingNumber} человек"
        binding.salary.text = item.salary.full
        binding.address.text = item.address.town
        binding.company.text = item.company
        binding.experience.text = item.experience.text

        val dateToLong = SimpleDateFormat("yyyy-MM-dd").let { formatter ->
            TimeUnit.MICROSECONDS.toSeconds(formatter.parse(item.publishedDate)?.time ?: 0)
        }
        val dateToText = DateUtils.getRelativeTimeSpanString(
            dateToLong,
            System.currentTimeMillis(),
            DateUtils.DAY_IN_MILLIS
        ).toString()
        binding.publishedDate.text = dateToText

    }

    /* val date = DateUtils.getRelativeTimeSpanString(
         TimeUnit.SECONDS.toMillis(mediaStoreImage.dateAdded),
         System.currentTimeMillis(),
         DateUtils.DAY_IN_MILLIS
     ).toString()

     private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
         SimpleDateFormat("dd.MM.yyyy").let { formatter ->
             TimeUnit.MICROSECONDS.toSeconds(formatter.parse("$day.$month.$year")?.time ?: 0)
         }*/

}

class VacancyDiffCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }

}