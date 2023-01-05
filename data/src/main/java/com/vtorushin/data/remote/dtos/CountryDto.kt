package com.vtorushin.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.vtorushin.data.utils.DataMapper
import com.vtorushin.domain.models.Country

data class CountryDto(
    @SerializedName("numeric")
    val numeric: String,
    @SerializedName("alpha2")
    val alpha2: String,
    @SerializedName("name")
    val countryName: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int
) : DataMapper<Country> {
    override fun mapToDomain() =
        Country(numeric, alpha2, countryName, emoji, currency, latitude, longitude)
}

fun Country.toDto() = CountryDto(numeric, alpha2, name, emoji, currency, latitude, longitude)