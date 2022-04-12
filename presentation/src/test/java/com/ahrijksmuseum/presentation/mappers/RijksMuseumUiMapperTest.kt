package com.ahrijksmuseum.presentation.mappers

import com.ahrijksmuseum.domain.models.ArtObject
import com.ahrijksmuseum.presentation.models.ArtObjectUiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class RijksMuseumUiMapperTest {

    @Test
    fun `when executed then it should return correct mapped ui model`() {
        val artObject = buildArtObject()
        val artObjectUiModel = buildArtObjectUiModel()
        val mappedObject = RijksMuseumUiMapper.map(artObject)

        assertEquals(artObjectUiModel, mappedObject)
    }

    private fun buildArtObject() = ArtObject(
        id = "id",
        objectNumber = "objectNumber",
        title = "title",
        longTitle = "longTitle",
        principalOrFirstMaker = "principalOrFirstMaker",
        imageUrl = "imageUrl"
    )

    private fun buildArtObjectUiModel() = ArtObjectUiModel.ArtObject(
        id = "id",
        objectNumber = "objectNumber",
        title = "title",
        longTitle = "longTitle",
        principalOrFirstMaker = "principalOrFirstMaker",
        imageUrl = "imageUrl"
    )

}
