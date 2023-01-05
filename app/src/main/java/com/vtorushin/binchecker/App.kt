package com.vtorushin.binchecker

import android.app.Application
import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.binchecker.di.bincheck.BinCheckComponent
import com.vtorushin.binchecker.di.bincheck.BinCheckComponentOwner
import com.vtorushin.binchecker.di.singleton.AppComponent
import com.vtorushin.binchecker.di.singleton.DaggerAppComponent

class App : Application(), BinCheckComponentOwner {
    private val appComponent: AppComponent = DaggerAppComponent
        .create()
    private var binCheckComponent: BinCheckComponent? = null

    override fun addComponent(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        applicationContext: Context
    ): BinCheckComponent {
        if (binCheckComponent == null) {
            binCheckComponent =
                appComponent
                    .binComponentFactory
                    .create(savedStateRegistryOwner, applicationContext)
        }
        return binCheckComponent!!
    }

    override fun clearComponent() {
        binCheckComponent = null
    }
}