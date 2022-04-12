package com.ahrijksmuseum.data.repository.datasources.local.mappers

import com.ahrijksmuseum.data.repository.datasources.local.models.ArtObjectDetailsDb
import com.ahrijksmuseum.domain.models.ArtObjectDetails

object ArtObjectDetailsDbMapper {

    fun mapToDomain(dbModel: ArtObjectDetailsDb) =
        ArtObjectDetails(
            id = dbModel.id,
            objectNumber = dbModel.objectNumber,
            title = dbModel.title,
            longTitle = dbModel.longTitle,
            subTitle = dbModel.subTitle,
            scLabelLine = dbModel.scLabelLine,
            description = dbModel.description,
            principalOrFirstMaker = dbModel.principalOrFirstMaker,
            materials = dbModel.materials,
            techniques = dbModel.techniques,
            productionPlaces = dbModel.productionPlaces,
            dating = dbModel.dating,
            documentation = dbModel.documentation
        )

    fun mapToDb(domainModel: ArtObjectDetails) =
        ArtObjectDetailsDb(
            id = domainModel.id,
            objectNumber = domainModel.objectNumber,
            title = domainModel.title,
            longTitle = domainModel.longTitle,
            subTitle = domainModel.subTitle,
            scLabelLine = domainModel.scLabelLine,
            description = domainModel.description,
            principalOrFirstMaker = domainModel.principalOrFirstMaker,
            materials = domainModel.materials,
            techniques = domainModel.techniques,
            productionPlaces = domainModel.productionPlaces,
            dating = domainModel.dating,
            documentation = domainModel.documentation
        )
}