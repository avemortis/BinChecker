package com.vtorushin.data.db.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.vtorushin.data.db.BinDatabase
import com.vtorushin.data.db.dtos.BinLookupEntity
import com.vtorushin.domain.repositories.BinCheckHistoryRepository

@OptIn(ExperimentalPagingApi::class)
class BinLookupRemoteMediator :
    RemoteMediator<Int, BinLookupEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BinLookupEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.uid
                }
            }
            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}