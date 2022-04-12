package com.ahrijksmuseum.presentation.mappers

import com.ahrijksmuseum.domain.models.ArtObject
import com.ahrijksmuseum.presentation.models.ArtObjectUiModel

object RijksMuseumUiMapper {

    fun map(artObjects: List<ArtObject>) = artObjects.map {
        map(it)
    }

    fun map(artObject: ArtObject): ArtObjectUiModel.ArtObject =
        ArtObjectUiModel.ArtObject(
            id = artObject.id,
            objectNumber = artObject.objectNumber,
            title = artObject.title,
            longTitle = artObject.longTitle,
            principalOrFirstMaker = artObject.principalOrFirstMaker,
            imageUrl = artObject.imageUrl
        )
}