package com.ahrijksmuseum.data.repository.datasources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahrijksmuseum.domain.models.ArtObject
import retrofit2.HttpException
import java.io.IOException

class PagingDataSource(
    private val remoteDataSource: RemoteDataSource,
) : PagingSource<Int, ArtObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObject> {
        return try {
            val position = params.key ?: INIT_PAGE
            val data = remoteDataSource.loadArtObjects(params.key ?: 1, PAGE_SIZE)

            LoadResult.Page(
                data = data,
                prevKey = if (position == INIT_PAGE) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object {
        private const val INIT_PAGE = 1
        const val PAGE_SIZE = 10
    }

}
