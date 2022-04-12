package com.ahrijksmuseum.data.repository.datasources.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtObjectsResponse(
    @Json(name = "count") val totalCount: Long? = null,
    @Json(name = "artObjects") val artObjects: List<ArtObjectResponse>? = null
)