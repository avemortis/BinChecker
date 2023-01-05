package com.vtorushin.data.db.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vtorushin.data.db.BinDatabase
import com.vtorushin.data.db.dtos.BinLookupEntity
import com.vtorushin.domain.repositories.BinCheckHistoryRepository
import com.vtorushin.domain.usecases.GetBinHistoryCheckUseCase

class BinLookupPagingSource(private val database: BinCheckHistoryRepository<List<BinLookupEntity>>) :
    PagingSource<Int, BinLookupEntity>() {
    override fun getRefreshKey(state: PagingState<Int, BinLookupEntity>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BinLookupEntity> {
        val page = params.key ?: 0

        return try {
            val entities =
                GetBinHistoryCheckUseCase(database).getPage(
                    params.loadSize,
                    page * params.loadSize
                )
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}