package com.shu.data.db.models

import androidx.room.ColumnInfo


data class AddressDbo (

  @ColumnInfo("town"   ) var town   : String? = null,
  @ColumnInfo("street" ) var street : String? = null,
  @ColumnInfo("house"  ) var house  : String? = null

)