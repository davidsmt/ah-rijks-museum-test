package com.ahrijksmuseum.data.repository.datasources.local

import com.ahrijksmuseum.data.repository.datasources.local.mappers.ArtObjectDetailsDbMapper
import com.ahrijksmuseum.domain.models.ArtObjectDetails

class LocalDataSource(appDatabase: AppDatabase) {

    private val artObjectDetailsDao = appDatabase.artObjectDetailsDao()

    fun loadArtObjectDetails(objectNumber: String): ArtObjectDetails? {
        return artObjectDetailsDao.loadArtObject(objectNumber)?.let {
            ArtObjectDetailsDbMapper.mapToDomain(it)
        }
    }

    fun updateArtObjectDetails(artObjectDetails: ArtObjectDetails) {
        artObjectDetailsDao.insert(ArtObjectDetailsDbMapper.mapToDb(artObjectDetails))
    }

}