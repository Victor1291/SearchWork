package com.shu.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.entity.models.Offer

@Entity(tableName = "offers")
data class OfferDbo(
    @PrimaryKey
    @ColumnInfo("id") val id: String ,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("link") val link: String
)

fun OfferDbo.fromDb() : Offer {
    return Offer(
        id = this.id,
        title = this.title,
        link = this.link
    )
}