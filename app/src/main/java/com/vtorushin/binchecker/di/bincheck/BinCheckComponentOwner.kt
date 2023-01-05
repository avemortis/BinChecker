package com.vtorushin.binchecker.di.bincheck

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner

interface BinCheckComponentOwner {
    fun addComponent(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        applicationContext: Context
    ): BinCheckComponent

    fun clearComponent()
}