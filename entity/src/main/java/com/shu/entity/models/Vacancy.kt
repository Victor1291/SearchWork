package com.shu.entity.models

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.Date

data class Vacancy(
    override val hasId: String = "vacancy",
    var id: String,
    var lookingNumber: Int,
    var title: String,
    var address: Address,
    var company: String,
    var experience: Experience,
    var publishedDate: String,
    var isFavorite: Boolean,
    var salary: Salary,
    var schedules: List<String>,
    var appliedNumber: Int,
    var description: String,
    var responsibilities: String,
    var questions: List<String>
) : HasStringId {

    val published: String = convert()
    private fun convert(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date: Date? = sdf.parse(publishedDate)
        val startDate: Long = date?.getTime() ?: 0

        val dateToText = DateUtils.getRelativeTimeSpanString(
            startDate,
            System.currentTimeMillis(),
            DateUtils.DAY_IN_MILLIS
        ).toString()
        return dateToText
    }
}
