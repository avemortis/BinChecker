package com.vtorushin.domain.models

data class BinLookupResponse(
    val number: Number,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean?,
    val country: Country,
    val bank: Bank
)
