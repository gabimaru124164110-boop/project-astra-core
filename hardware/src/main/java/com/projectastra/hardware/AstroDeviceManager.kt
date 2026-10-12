package com.projectastra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectastra.database.entity.DssCacheEntity

@Dao
interface DssCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cache: DssCacheEntity)

    @Query("SELECT * FROM dss_cache WHERE region_name = :regionName LIMIT 1")
    suspend fun fetchRegion(regionName: String): DssCacheEntity?

    @Query("SELECT * FROM dss_cache ORDER BY cached_at_epoch DESC LIMIT 25")
    suspend fun recentTiles(): List<DssCacheEntity>
}
