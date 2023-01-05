package com.vtorushin.domain.repositories

import com.vtorushin.domain.models.BinLookupResponse

interface BinCheckHistoryRepository<out T> {
    suspend fun insert(binLookupResponse: BinLookupResponse)
    suspend fun getAll(): T
    suspend fun getPage(limit: Int, offset: Int): T
}