package com.vtorushin.binchecker.utils

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.binchecker.presentation.ui.fragments.bincheck.BinCheckViewModel
import com.vtorushin.data.repositories.BinCheckHistoryRepositoryImpl
import com.vtorushin.domain.repositories.BinCheckRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ViewModelFactory @AssistedInject constructor(
    @Assisted private val savedStateRegistryOwner: SavedStateRegistryOwner,
    @Assisted private val repository: BinCheckRepository,
    @Assisted private val database: BinCheckHistoryRepositoryImpl
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return BinCheckViewModel(savedStateRegistryOwner, repository, database) as T
    }


    @AssistedFactory
    interface Factory {
        fun create(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            repository: BinCheckRepository,
            database: BinCheckHistoryRepositoryImpl
        ): ViewModelFactory
    }
}