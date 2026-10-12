package com.projectastra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dss_cache")
data class DssCacheEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    @ColumnInfo(name = "region_name") val regionName: String,
    @ColumnInfo(name = "center_ra_deg") val centerRaDeg: Double,
    @ColumnInfo(name = "center_dec_deg") val centerDecDeg: Double,
    @ColumnInfo(name = "tile_size_px") val tileSizePx: Int,
    @ColumnInfo(name = "file_uri") val fileUri: String,
    @ColumnInfo(name = "cached_at_epoch") val cachedAtEpoch: Long,
)
