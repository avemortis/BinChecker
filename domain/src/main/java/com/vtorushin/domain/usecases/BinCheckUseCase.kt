package com.vtorushin.domain.usecases

import com.vtorushin.domain.repositories.BinCheckRepository
import javax.inject.Inject

class BinCheckUseCase @Inject constructor(
    private val repository: BinCheckRepository
) {
    suspend operator fun invoke(bin: String) = repository.check(bin)
}