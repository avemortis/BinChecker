package com.vtorushin.data.remote.apiservices

import com.vtorushin.data.remote.dtos.BinLookupResponseDto
import com.vtorushin.domain.models.BinLookupResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface BinCheckApiService {
    @GET
    suspend fun checkBin(@Url url: String): BinLookupResponseDto
}