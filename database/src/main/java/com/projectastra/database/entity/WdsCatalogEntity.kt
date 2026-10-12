package com.projectastra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wds_catalog")
data class WdsCatalogEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "designation") val designation: String,
    @ColumnInfo(name = "primary_name") val primaryName: String,
    @ColumnInfo(name = "component_name") val componentName: String,
    @ColumnInfo(name = "separation_arcsec") val separationArcsec: Double,
    @ColumnInfo(name = "position_angle_deg") val positionAngleDeg: Double,
    @ColumnInfo(name = "magnitude_primary") val magnitudePrimary: Double,
    @ColumnInfo(name = "magnitude_secondary") val magnitudeSecondary: Double,
    @ColumnInfo(name = "is_hierarchical") val isHierarchical: Boolean = false,
)
