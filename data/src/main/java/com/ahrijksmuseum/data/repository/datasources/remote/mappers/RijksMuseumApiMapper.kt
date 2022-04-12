package com.ahrijksmuseum.data.repository.datasources.remote.mappers

import com.ahrijksmuseum.data.repository.datasources.remote.models.ArtObjectDetailsResponse
import com.ahrijksmuseum.data.repository.datasources.remote.models.ArtObjectResponse
import com.ahrijksmuseum.domain.models.ArtObject
import com.ahrijksmuseum.domain.models.ArtObjectDetails

object RijksMuseumApiMapper {

    fun mapToDomain(apiModel: ArtObjectResponse): ArtObject? {
        return try {
            ArtObject(
                id = requireNotNull(apiModel.id) { "id is required" },
                objectNumber = requireNotNull(
                    apiModel.objectNumber
                ) { "objectNumber is required" },
                title = apiModel.title,
                longTitle = apiModel.longTitle,
                principalOrFirstMaker = requireNotNull(
                    apiModel.principalOrFirstMaker
                ) { "principalOrFirstMaker is required" },
                imageUrl = apiModel.headerImage?.url
            )
        } catch (ex: NullPointerException) {
            null
        }
    }

    fun mapToDomain(
        apiModel: ArtObjectDetailsResponse
    ): ArtObjectDetails {
        return ArtObjectDetails(
            id = requireNotNull(apiModel.artObject?.id) { "id is required" },
            objectNumber = requireNotNull(
                apiModel.artObject?.objectNumber
            ) { "objectNumber is required" },
            title = apiModel.artObject?.title,
            longTitle = apiModel.artObject?.longTitle,
            subTitle = apiModel.artObject?.subTitle,
            scLabelLine = apiModel.artObject?.scLabelLine,
            description = apiModel.artObject?.description,
            principalOrFirstMaker = requireNotNull(
                apiModel.artObject?.principalOrFirstMaker
            ) { "principalOrFirstMaker is required" },
            materials = apiModel.artObject?.materials?.filter {
                !it.isNullOrBlank()
            }?.joinToString(", "),
            techniques = apiModel.artObject?.techniques?.filter {
                !it.isNullOrBlank()
            }?.joinToString(", "),
            productionPlaces = apiModel.artObject?.productionPlaces?.filter {
                !it.isNullOrBlank()
            }?.joinToString(", "),
            dating = apiModel.artObject?.dating?.presentingDate,
            documentation = apiModel.artObject?.documentation?.filter {
                !it.isNullOrBlank()
            }?.joinToString(", "),
        )
    }

}