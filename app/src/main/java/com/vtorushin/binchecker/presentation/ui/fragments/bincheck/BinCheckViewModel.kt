package com.vtorushin.binchecker.presentation.ui.fragments.bincheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.data.db.pagingsource.BinLookupPagingSource
import com.vtorushin.data.db.pagingsource.BinLookupRemoteMediator
import com.vtorushin.data.repositories.BinCheckHistoryRepositoryImpl
import com.vtorushin.domain.models.BinLookupResponse
import com.vtorushin.domain.repositories.BinCheckRepository
import com.vtorushin.domain.usecases.AddNewBinUseCase
import com.vtorushin.domain.usecases.BinCheckUseCase
import com.vtorushin.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinCheckViewModel @Inject constructor(
    private val savedStateRegistryOwner: SavedStateRegistryOwner,
    private val repository: BinCheckRepository,
    private val database: BinCheckHistoryRepositoryImpl
) : ViewModel() {
    private val _binLookup: MutableStateFlow<Resource<BinLookupResponse>> =
        MutableStateFlow(Resource.notStarted())
    val binLookup = _binLookup.asStateFlow()

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        ),
        remoteMediator = BinLookupRemoteMediator()
    ) { BinLookupPagingSource(database) }
        .flow

    fun checkBin(bin: String) {
        viewModelScope.launch {
            _binLookup.emit(Resource.loading())
            try {
                val data = BinCheckUseCase(repository).invoke(bin)
                _binLookup.emit(Resource.success(data))
                AddNewBinUseCase(database).invoke(data)
            } catch (e: Exception) {
                _binLookup.emit(Resource.error(message = e.message ?: "Error!"))
            }
        }
    }
}