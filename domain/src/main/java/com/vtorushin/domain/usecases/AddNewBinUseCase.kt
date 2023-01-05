package com.vtorushin.domain.usecases

import com.vtorushin.domain.models.BinLookupResponse
import com.vtorushin.domain.repositories.BinCheckHistoryRepository
import javax.inject.Inject

class AddNewBinUseCase @Inject constructor(
    private val repository: BinCheckHistoryRepository<*>
) {
    suspend operator fun invoke(response: BinLookupResponse) = repository.insert(response)
}