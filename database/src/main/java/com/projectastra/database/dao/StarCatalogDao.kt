package com.projectastra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectastra.database.entity.StarCatalogEntity

@Dao
interface StarCatalogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(star: StarCatalogEntity)

    @Query("SELECT * FROM star_catalog WHERE is_usable = 1 ORDER BY magnitude ASC LIMIT 500")
    suspend fun brightestStars(): List<StarCatalogEntity>

    @Query("SELECT * FROM star_catalog WHERE designation LIKE :query LIMIT 25")
    suspend fun searchByDesignation(query: String): List<StarCatalogEntity>
}
