package com.ahrijksmuseum.data.repository.datasources.remote

import com.ahrijksmuseum.data.network.RijksMuseumApi
import com.ahrijksmuseum.data.repository.datasources.remote.mappers.RijksMuseumApiMapper
import com.ahrijksmuseum.domain.models.ArtObject
import com.ahrijksmuseum.domain.models.ArtObjectDetails

class RemoteDataSource(private val rijksMuseumApi: RijksMuseumApi) {

    suspend fun loadArtObjects(page: Int, pageSize: Int): List<ArtObject> {
        return rijksMuseumApi.loadArtObjects(page, pageSize).artObjects?.mapNotNull {
            RijksMuseumApiMapper.mapToDomain(it)
        } ?: emptyList()
    }

    suspend fun loadArtObjectDetails(objectNumber: String): ArtObjectDetails {
        val artObjectDetails = rijksMuseumApi.loadArtObjectDetails(objectNumber)
        return RijksMuseumApiMapper.mapToDomain(
            apiModel = artObjectDetails
        )
    }
}