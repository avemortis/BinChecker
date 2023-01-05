package com.vtorushin.domain.usecases

import com.vtorushin.domain.repositories.BinCheckHistoryRepository
import javax.inject.Inject

class GetBinHistoryCheckUseCase<out T> @Inject constructor(
    private val repository: BinCheckHistoryRepository<T>
) {
    suspend fun getAll() = repository.getAll()
    suspend fun getPage(limit: Int, offset: Int) = repository.getPage(limit, offset)
}