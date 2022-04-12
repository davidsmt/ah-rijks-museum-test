package com.ahrijksmuseum.domain

import androidx.paging.PagingData
import com.ahrijksmuseum.domain.models.ArtObject
import com.ahrijksmuseum.domain.models.ArtObjectDetails
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loadArtObjects(): Flow<PagingData<ArtObject>>

    suspend fun loadArtObjectDetails(objectNumber: String, forceRefresh: Boolean): Flow<ArtObjectDetails>

}