package com.ahrijksmuseum.domain.models

data class ArtObject(
    val id: String,
    val objectNumber: String,
    val title: String?,
    val longTitle: String?,
    val principalOrFirstMaker: String,
    val imageUrl: String?
)