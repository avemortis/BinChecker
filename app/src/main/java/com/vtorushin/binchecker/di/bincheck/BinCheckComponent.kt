package com.vtorushin.binchecker.di.bincheck

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.binchecker.presentation.ui.fragments.bincheck.BinCheckFragment
import com.vtorushin.binchecker.presentation.ui.fragments.bincheck.BinCheckViewModel
import com.vtorushin.binchecker.utils.ViewModelFactory
import com.vtorushin.domain.repositories.BinCheckRepository
import dagger.BindsInstance
import dagger.Subcomponent

@BinCheckScope
@Subcomponent(modules = [BinCheckModule::class])
interface BinCheckComponent {
    fun inject(fragment: BinCheckFragment)
    fun inject(viewModel: BinCheckViewModel)
    fun repository(): BinCheckRepository
    fun viewModelFactory(): ViewModelFactory.Factory
    fun viewModel(): BinCheckViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
            @BindsInstance applicationContext: Context
        ): BinCheckComponent
    }
}

internal fun Fragment.binCheckComponent() =
    (requireContext().applicationContext as BinCheckComponentOwner)
        .addComponent(this, requireContext().applicationContext)