package com.ahrijksmuseum.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahrijksmuseum.data.network.networkBoundResource
import com.ahrijksmuseum.data.repository.datasources.local.LocalDataSource
import com.ahrijksmuseum.data.repository.datasources.remote.PagingDataSource
import com.ahrijksmuseum.data.repository.datasources.remote.RemoteDataSource
import com.ahrijksmuseum.domain.Repository
import com.ahrijksmuseum.domain.models.ArtObject
import com.ahrijksmuseum.domain.models.ArtObjectDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun loadArtObjects(): Flow<PagingData<ArtObject>> {
        return Pager(PagingConfig(PagingDataSource.PAGE_SIZE)) {
            PagingDataSource(remoteDataSource = remoteDataSource)
        }.flow
    }

    override suspend fun loadArtObjectDetails(
        objectNumber: String,
        forceRefresh: Boolean
    ): Flow<ArtObjectDetails> {
        return networkBoundResource(
            query = {
                when (val dbModel = localDataSource.loadArtObjectDetails(objectNumber)) {
                    null -> flowOf()
                    else -> flowOf(dbModel)
                }
            },
            fetch = {
                remoteDataSource.loadArtObjectDetails(objectNumber)
            },
            saveFetchResult = {
                localDataSource.updateArtObjectDetails(it)
            },
            shouldFetch = {
                it == null || forceRefresh
            }
        )
    }

}