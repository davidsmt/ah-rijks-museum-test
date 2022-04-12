package com.ahrijksmuseum.presentation.mappers

import com.ahrijksmuseum.domain.models.ArtObjectDetails
import com.ahrijksmuseum.presentation.models.ArtObjectDetailsUiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtObjectDetailsUiMapperTest {

    @Test
    fun `when executed then it should return correct mapped ui model`() {
        val artObjectDetails = buildArtObjectDetails()
        val artObjectDetailsUiModel = buildArtObjectDetailsUiModel()
        val mappedObject = ArtObjectDetailsUiMapper.map(artObjectDetails)

        assertEquals(artObjectDetailsUiModel, mappedObject)
    }

    private fun buildArtObjectDetails() = ArtObjectDetails(
        id = "id",
        objectNumber = "objectNumber",
        title = "title",
        longTitle = "longTitle",
        subTitle = "subTitle",
        scLabelLine = "scLabelLine",
        description = "description",
        principalOrFirstMaker = "principalOrFirstMaker",
        materials = "materials",
        techniques = "techniques",
        productionPlaces = "productionPlaces",
        dating = "dating",
        documentation = "documentation"
    )

    private fun buildArtObjectDetailsUiModel() = ArtObjectDetailsUiModel(
        id = "id",
        objectNumber = "objectNumber",
        title = "title",
        longTitle = "longTitle",
        subTitle = "subTitle",
        scLabelLine = "scLabelLine",
        description = "description",
        principalOrFirstMaker = "principalOrFirstMaker",
        materials = "materials",
        techniques = "techniques",
        productionPlaces = "productionPlaces",
        dating = "dating",
        documentation = "documentation"
    )

}
