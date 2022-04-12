package com.ahrijksmuseum.presentation.mappers

import com.ahrijksmuseum.domain.models.ArtObjectDetails
import com.ahrijksmuseum.presentation.models.ArtObjectDetailsUiModel

object ArtObjectDetailsUiMapper {

    fun map(artObjectDetails: ArtObjectDetails): ArtObjectDetailsUiModel =
        ArtObjectDetailsUiModel(
            id = artObjectDetails.id,
            objectNumber = artObjectDetails.objectNumber,
            title = artObjectDetails.title,
            longTitle = artObjectDetails.longTitle,
            subTitle = artObjectDetails.subTitle,
            scLabelLine = artObjectDetails.scLabelLine,
            description = artObjectDetails.description,
            principalOrFirstMaker = artObjectDetails.principalOrFirstMaker,
            materials = artObjectDetails.materials,
            techniques = artObjectDetails.techniques,
            productionPlaces = artObjectDetails.productionPlaces,
            dating = artObjectDetails.dating,
            documentation = artObjectDetails.documentation
        )

}