package com.vtorushin.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.data.utils.DataMapper
import com.vtorushin.domain.models.Number

data class NumberDto(
    @SerializedName("length")
    val length: Int?,
    @SerializedName("lunh")
    val lunh: Boolean?
) : DataMapper<Number> {
    override fun mapToDomain() = Number(
        length, lunh
    )
}

fun Number.toDto() = NumberDto(length, lunh)