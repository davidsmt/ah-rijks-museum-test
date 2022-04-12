package com.ahrijksmuseum.data.repository.datasources.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtObjectResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "objectNumber") val objectNumber: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "longTitle") val longTitle: String? = null,
    @Json(name = "principalOrFirstMaker") val principalOrFirstMaker: String? = null,
    @Json(name = "headerImage") val headerImage: HeaderImageResponse? = null
) {

    @JsonClass(generateAdapter = true)
    data class HeaderImageResponse(
        @Json(name = "url") val url: String? = null
    )

}