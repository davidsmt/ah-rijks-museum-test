package com.ahrijksmuseum.data.repository.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahrijksmuseum.data.repository.datasources.local.daos.ArtObjectDetailsDao
import com.ahrijksmuseum.data.repository.datasources.local.models.ArtObjectDetailsDb

@Database(
    entities = [
        ArtObjectDetailsDb::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun artObjectDetailsDao(): ArtObjectDetailsDao

    companion object {

        const val DATABASE_NAME = "rijks-museum-app-database"

    }
}