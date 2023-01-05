package com.vtorushin.binchecker.di.bincheck

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.GsonBuilder
import com.vtorushin.binchecker.presentation.ui.fragments.bincheck.BinCheckViewModel
import com.vtorushin.binchecker.utils.ViewModelFactory
import com.vtorushin.data.db.BinDatabase
import com.vtorushin.data.db.BinDatabase.Companion.DATABASE_NAME
import com.vtorushin.data.db.daos.BinLookupDao
import com.vtorushin.data.db.dtos.BinLookupEntity
import com.vtorushin.data.remote.apiservices.BinCheckApiService
import com.vtorushin.data.repositories.BinCheckHistoryRepositoryImpl
import com.vtorushin.data.repositories.BinCheckRepositoryImpl
import com.vtorushin.data.repositories.BinCheckRepositoryImpl.Companion.BASE_URL
import com.vtorushin.domain.repositories.BinCheckHistoryRepository
import com.vtorushin.domain.repositories.BinCheckRepository
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class BinCheckModule {
    @Provides
    @BinCheckScope
    fun provideBinCheckService(): BinCheckApiService {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BinCheckApiService::class.java)
    }

    @Provides
    @BinCheckScope
    fun provideBinCheckRepository(): BinCheckRepository {
        return BinCheckRepositoryImpl(
            provideBinCheckService()
        )
    }

    @Provides
    @BinCheckScope
    fun provideBinCheckHistoryRepository(applicationContext: Context): BinCheckHistoryRepositoryImpl {
        return BinCheckHistoryRepositoryImpl(provideBinLookupDatabase(applicationContext))
    }

    @Provides
    @BinCheckScope
    fun provideViewModel(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        applicationContext: Context
    ): BinCheckViewModel {
        return ViewModelFactory(
            savedStateRegistryOwner,
            provideBinCheckRepository(),
            provideBinCheckHistoryRepository(applicationContext)
        ).create(
            BinCheckViewModel::class.java
        )
    }

    @Provides
    @BinCheckScope
    fun provideBinLookupDatabase(applicationContext: Context): BinDatabase {
        return Room.databaseBuilder(
            applicationContext,
            BinDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}