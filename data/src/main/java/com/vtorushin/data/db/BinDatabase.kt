package com.vtorushin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vtorushin.data.db.daos.BinLookupDao
import com.vtorushin.data.db.dtos.BinLookupEntity
import javax.inject.Inject

@Database(entities = [BinLookupEntity::class], version = 1)
abstract class BinDatabase @Inject constructor() : RoomDatabase() {
    abstract fun binLookupDao(): BinLookupDao
    companion object {
        const val DATABASE_NAME = "bin-lookup-history"
    }
}