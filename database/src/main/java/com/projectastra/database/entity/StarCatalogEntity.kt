package com.projectastra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "star_catalog")
data class StarCatalogEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "hipparcos_id") val hipparcosId: Int?,
    @ColumnInfo(name = "designation") val designation: String,
    @ColumnInfo(name = "ra_deg") val raDeg: Double,
    @ColumnInfo(name = "dec_deg") val decDeg: Double,
    @ColumnInfo(name = "magnitude") val magnitude: Double,
    @ColumnInfo(name = "bv_index") val bvIndex: Double,
    @ColumnInfo(name = "proper_motion_ra") val properMotionRa: Double,
    @ColumnInfo(name = "proper_motion_dec") val properMotionDec: Double,
    @ColumnInfo(name = "is_usable") val isUsable: Boolean = true,
)
