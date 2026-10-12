package com.projectastra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectastra.database.entity.WdsCatalogEntity

@Dao
interface WdsCatalogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entry: WdsCatalogEntity)

    @Query("SELECT * FROM wds_catalog WHERE primary_name LIKE :query OR designation LIKE :query LIMIT 25")
    suspend fun searchByName(query: String): List<WdsCatalogEntity>

    @Query("SELECT * FROM wds_catalog WHERE is_hierarchical = 1 ORDER BY separation_arcsec ASC LIMIT 100")
    suspend fun hierarchicalSystems(): List<WdsCatalogEntity>
}
