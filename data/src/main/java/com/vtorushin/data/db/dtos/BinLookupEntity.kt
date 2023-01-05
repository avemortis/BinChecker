package com.vtorushin.data.db.dtos

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vtorushin.data.utils.DataMapper
import com.vtorushin.domain.models.Bank
import com.vtorushin.domain.models.BinLookupResponse
import com.vtorushin.domain.models.Country
import com.vtorushin.domain.models.Number

@Entity
data class BinLookupEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    val uid: Int,
    @Embedded val number: Number?,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean?,
    @Embedded(prefix = "country") val country: Country,
    @Embedded(prefix = "bank") val bank: Bank?
) : DataMapper<BinLookupResponse> {
    override fun mapToDomain() = BinLookupResponse(
        number ?: Number(null, null),
        scheme,
        type,
        brand,
        prepaid,
        country,
        bank ?: Bank(null, null, null, null)
    )
}

fun BinLookupResponse.toEntity() =
    BinLookupEntity(0, number, scheme, type, brand, prepaid, country, bank)