package com.vtorushin.domain.repositories

import com.vtorushin.domain.models.BinLookupResponse

interface BinCheckRepository {
    suspend fun check(bin: String): BinLookupResponse
}