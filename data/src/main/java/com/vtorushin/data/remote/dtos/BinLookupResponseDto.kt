package com.vtorushin.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.data.utils.DataMapper
import com.vtorushin.domain.models.BinLookupResponse

data class BinLookupResponseDto(
    @SerializedName("number")
    val number: NumberDto,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("prepaid")
    val prepaid: Boolean?,
    @SerializedName("country")
    val country: CountryDto,
    @SerializedName("bank")
    val bank: BankDto
) : DataMapper<BinLookupResponse> {
    override fun mapToDomain() = BinLookupResponse(
        number.mapToDomain(),
        scheme,
        type,
        brand,
        prepaid,
        country.mapToDomain(),
        bank.mapToDomain()
    )
}

fun BinLookupResponse.toDto() = BinLookupResponseDto(number.toDto(), scheme, type, brand, prepaid, country.toDto(), bank.toDto())