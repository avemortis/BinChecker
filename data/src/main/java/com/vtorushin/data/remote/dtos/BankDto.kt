package com.vtorushin.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.data.utils.DataMapper
import com.vtorushin.domain.models.Bank

data class BankDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("city")
    val city: String?
) : DataMapper<Bank> {
    override fun mapToDomain() = Bank(name, url, phone, city)
}

fun Bank.toDto() = BankDto(name, url, phone, city)