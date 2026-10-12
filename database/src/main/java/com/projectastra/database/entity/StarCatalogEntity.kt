package com.projectastra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "star_catalog")
data class StarCatalogEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "designation") val designation: String,
    @ColumnInfo(name = "common_name") val commonName: String? = null,
    @ColumnInfo(name = "ra_deg") val raDeg: Double,
    @ColumnInfo(name = "dec_deg") val decDeg: Double,
    @ColumnInfo(name = "magnitude") val magnitude: Double,
    @ColumnInfo(name = "bv_index") val bvIndex: Double,
    @ColumnInfo(name = "spectral_type") val spectralType: String? = null,
    @ColumnInfo(name = "object_type") val objectType: String = "STAR",
    @ColumnInfo(name = "distance_ly") val distanceLy: Double = 0.0,
)
