package com.projectastra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projectastra.database.dao.DssCacheDao
import com.projectastra.database.dao.StarCatalogDao
import com.projectastra.database.entity.DssCacheEntity
import com.projectastra.database.entity.StarCatalogEntity

@Database(
    entities = [StarCatalogEntity::class, DssCacheEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class AstraDatabase : RoomDatabase() {
    abstract fun starCatalogDao(): StarCatalogDao
    abstract fun dssCacheDao(): DssCacheDao
}
