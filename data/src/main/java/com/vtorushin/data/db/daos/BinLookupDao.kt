package com.vtorushin.data.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vtorushin.data.db.dtos.BinLookupEntity

@Dao
interface BinLookupDao {
    @Query("SELECT * FROM BinLookupEntity")
    suspend fun getAll(): List<BinLookupEntity>

    @Query("SELECT * FROM BinLookupEntity ORDER BY uid DESC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<BinLookupEntity>

    @Query("SELECT * FROM BinLookupEntity")
    fun pagingSource(): PagingSource<Int, BinLookupEntity>

    @Insert
    suspend fun insert(binLookupEntity: BinLookupEntity)
}