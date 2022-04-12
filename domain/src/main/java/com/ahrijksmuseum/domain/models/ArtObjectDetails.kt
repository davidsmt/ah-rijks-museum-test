package com.ahrijksmuseum.domain.models

data class ArtObjectDetails(
    val id: String,
    val objectNumber: String,
    val title: String?,
    val longTitle: String?,
    val subTitle: String?,
    val scLabelLine: String?,
    val description: String?,
    val principalOrFirstMaker: String,
    val materials: String?,
    val techniques: String?,
    val productionPlaces: String?,
    val dating: String?,
    val documentation: String?
)