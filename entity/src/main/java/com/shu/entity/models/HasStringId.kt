package com.shu.entity.models

interface HasStringId {
    val hasId: String
    override fun equals(other: Any?): Boolean
}
