package com.ahrijksmuseum.data.repository.datasources.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtObjectDetailsResponse(
    @Json(name = "artObject") val artObject: ArtObjectResponse?
) {

    @JsonClass(generateAdapter = true)
    data class ArtObjectResponse(
        @Json(name = "id") val id: String? = null,
        @Json(name = "objectNumber") val objectNumber: String? = null,
        @Json(name = "title") val title: String? = null,
        @Json(name = "longTitle") val longTitle: String? = null,
        @Json(name = "subTitle") val subTitle: String? = null,
        @Json(name = "scLabelLine") val scLabelLine: String? = null,
        @Json(name = "description") val description: String? = null,
        @Json(name = "principalOrFirstMaker") val principalOrFirstMaker: String? = null,
        @Json(name = "materials") val materials: List<String?> = emptyList(),
        @Json(name = "techniques") val techniques: List<String?> = emptyList(),
        @Json(name = "productionPlaces") val productionPlaces: List<String?> = emptyList(),
        @Json(name = "dating") val dating: DatingResponse? = null,
        @Json(name = "documentation") val documentation: List<String?> = emptyList()
    )

    @JsonClass(generateAdapter = true)
    data class DatingResponse(
        @Json(name = "presentingDate") val presentingDate: String? = null,
    )

}