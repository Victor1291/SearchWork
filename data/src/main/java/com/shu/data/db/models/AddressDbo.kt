package com.shu.data.db.models

import androidx.room.ColumnInfo
import com.shu.entity.models.Address
import com.shu.entity.models.Offer


data class AddressDbo(

    @ColumnInfo("town") var town: String? = null,
    @ColumnInfo("street") var street: String? = null,
    @ColumnInfo("house") var house: String? = null

)

fun AddressDbo.fromDb(): Address {
    return Address(
        town = this.town ?: "no",
        street = this.street ?: "no",
        house = this.house ?: "no"
    )
}