package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class GaiaStarRecord(
    val sourceId: Long,
    val designation: String,
    val rightAscensionDeg: Double,
    val declinationDeg: Double,
    val magnitude: Double,
    val properMotionRaMasYear: Double,
    val properMotionDecMasYear: Double,
    val parallaxMas: Double,
)

class GaiaCatalogIndex {
    private val records = mutableListOf<GaiaStarRecord>()

    fun load(records: List<GaiaStarRecord>) {
        this.records.clear()
        this.records.addAll(records)
    }

    fun queryByMagnitude(limit: Int = 100): List<GaiaStarRecord> =
        records.sortedBy { it.magnitude }.take(limit)

    fun queryNearby(rightAscensionDeg: Double, declinationDeg: Double, radiusDeg: Double): List<GaiaStarRecord> =
        records.filter { record ->
            val deltaRa = kotlin.math.abs(record.rightAscensionDeg - rightAscensionDeg)
            val deltaDec = kotlin.math.abs(record.declinationDeg - declinationDeg)
            val angularDistance = sqrt(
                (deltaRa * cos(Math.toRadians(declinationDeg))) * (deltaRa * cos(Math.toRadians(declinationDeg))) +
                    deltaDec * deltaDec,
            )
            angularDistance <= radiusDeg
        }

    fun properMotionAtEpoch(record: GaiaStarRecord, years: Double): Pair<Double, Double> {
        val raShift = record.properMotionRaMasYear * years / 3600000.0
        val decShift = record.properMotionDecMasYear * years / 3600000.0
        return (record.rightAscensionDeg + raShift) to (record.declinationDeg + decShift)
    }
}
