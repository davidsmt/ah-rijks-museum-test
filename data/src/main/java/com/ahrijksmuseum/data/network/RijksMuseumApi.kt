package com.ahrijksmuseum.data.network

import com.ahrijksmuseum.data.repository.datasources.remote.models.ArtObjectDetailsResponse
import com.ahrijksmuseum.data.repository.datasources.remote.models.ArtObjectsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksMuseumApi {

    @GET("collection?key=0fiuZFh4&s=artist")
    suspend fun loadArtObjects(
        @Query("p") page: Int,
        @Query("ps") pageSize: Int
    ): ArtObjectsResponse

    @GET("collection/{objectNumber}?key=0fiuZFh4")
    suspend fun loadArtObjectDetails(@Path("objectNumber") objectNumber: String): ArtObjectDetailsResponse

}