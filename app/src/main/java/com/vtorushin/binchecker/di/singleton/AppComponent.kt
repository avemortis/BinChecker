package com.vtorushin.binchecker.di.singleton

import com.vtorushin.binchecker.di.bincheck.BinCheckComponent
import com.vtorushin.binchecker.di.bincheck.BinCheckScope
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {
    @BinCheckScope
    val binComponentFactory: BinCheckComponent.Factory
}