package com.ahrijksmuseum.data.repository.datasources.local.daos

import androidx.room.*
import com.ahrijksmuseum.data.repository.datasources.local.models.ArtObjectDetailsDb

@Dao
interface ArtObjectDetailsDao {

    @Query("SELECT * FROM art_object_details WHERE objectNumber == :artObjectNumber")
    fun loadArtObject(artObjectNumber: String): ArtObjectDetailsDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artObjectDetails: ArtObjectDetailsDb): Long

    @Delete
    fun delete(artObjectDetails: ArtObjectDetailsDb)

    @Query("DELETE FROM art_object_details")
    fun deleteAll()

}