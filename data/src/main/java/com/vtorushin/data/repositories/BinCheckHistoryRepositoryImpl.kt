package com.vtorushin.data.repositories

import com.vtorushin.data.db.BinDatabase
import com.vtorushin.data.db.dtos.BinLookupEntity
import com.vtorushin.data.db.dtos.toEntity
import com.vtorushin.domain.models.BinLookupResponse
import com.vtorushin.domain.repositories.BinCheckHistoryRepository
import javax.inject.Inject

class BinCheckHistoryRepositoryImpl @Inject constructor(val database: BinDatabase) :
    BinCheckHistoryRepository<List<BinLookupEntity>> {
    override suspend fun insert(binLookupResponse: BinLookupResponse) {
        database.binLookupDao().insert(binLookupResponse.toEntity())
    }

    override suspend fun getAll() =
        database.binLookupDao().getAll()

    override suspend fun getPage(limit: Int, offset: Int) =
        database.binLookupDao().getPagedList(limit, offset)
}