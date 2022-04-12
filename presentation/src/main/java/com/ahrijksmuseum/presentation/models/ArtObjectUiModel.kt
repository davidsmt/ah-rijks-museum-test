package com.ahrijksmuseum.presentation.models

sealed class ArtObjectUiModel {

    data class ArtObject(
        val id: String,
        val objectNumber: String,
        val title: String?,
        val longTitle: String?,
        val principalOrFirstMaker: String,
        val imageUrl: String?
    ) : ArtObjectUiModel()

    data class ArtistHeader(
        val principalOrFirstMaker: String
    ) : ArtObjectUiModel()

}