package com.shu.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class OfferDbo(
    @PrimaryKey
    @ColumnInfo("id") var id: String? = null,
    @ColumnInfo("title") var title: String? = null,
    @ColumnInfo("link") var link: String? = null

)