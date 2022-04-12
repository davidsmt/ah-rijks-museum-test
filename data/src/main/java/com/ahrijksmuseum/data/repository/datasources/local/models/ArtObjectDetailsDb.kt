package com.ahrijksmuseum.data.repository.datasources.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "art_object_details")
data class ArtObjectDetailsDb(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "objectNumber") val objectNumber: String,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "longTitle") val longTitle: String? = null,
    @ColumnInfo(name = "subTitle") val subTitle: String? = null,
    @ColumnInfo(name = "scLabelLine") val scLabelLine: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "principalOrFirstMaker") val principalOrFirstMaker: String,
    @ColumnInfo(name = "materials") val materials: String? = null,
    @ColumnInfo(name = "techniques") val techniques: String? = null,
    @ColumnInfo(name = "productionPlaces") val productionPlaces: String? = null,
    @ColumnInfo(name = "dating") val dating: String? = null,
    @ColumnInfo(name = "documentation") val documentation: String? = null
)