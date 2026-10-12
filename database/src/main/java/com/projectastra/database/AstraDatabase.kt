package com.projectastra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projectastra.database.dao.StarCatalogDao
import com.projectastra.database.entity.StarCatalogEntity

@Database(
    entities = [StarCatalogEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AstraDatabase : RoomDatabase() {
    abstract fun starCatalogDao(): StarCatalogDao
}
