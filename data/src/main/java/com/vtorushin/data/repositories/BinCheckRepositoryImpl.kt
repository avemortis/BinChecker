package com.vtorushin.data.repositories

import com.vtorushin.data.remote.apiservices.BinCheckApiService
import com.vtorushin.domain.repositories.BinCheckRepository
import javax.inject.Inject

class BinCheckRepositoryImpl @Inject constructor(
    private val service: BinCheckApiService
): BinCheckRepository {
    override suspend fun check(bin: String) = service.checkBin(BASE_URL+bin).mapToDomain()
    companion object {
        const val BASE_URL = "https://lookup.binlist.net/"
    }
}