package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

data class ObservationScore(
    val targetName: String,
    val score: Double,
    val altitudeDeg: Double,
    val moonSeparationDeg: Double,
    val seeingScore: Double,
    val hardwareFit: Double,
)

class TargetRecommendationEngine {
    fun scoreTarget(
        targetAltitudeDeg: Double,
        moonIlluminationPercent: Double,
        seeingArcsec: Double,
        filterProfile: String,
        targetType: String,
    ): ObservationScore {
        val altitudeScore = (targetAltitudeDeg / 90.0).coerceIn(0.0, 1.0)
        val moonPenalty = (100.0 - moonIlluminationPercent) / 100.0
        val seeingScore = (3.0 - seeingArcsec.coerceIn(0.8, 4.5)) / 2.2
        val filterBonus = if (filterProfile.contains("dual-band", ignoreCase = true) || filterProfile.contains("narrowband", ignoreCase = true)) 0.15 else 0.05
        val typeBonus = when (targetType.lowercase()) {
            "nebula" -> 0.18
            "galaxy" -> 0.12
            "cluster" -> 0.08
            "planet" -> 0.06
            else -> 0.10
        }

        val total = (altitudeScore * 0.45 + moonPenalty * 0.2 + seeingScore.coerceIn(0.0, 1.0) * 0.2 + filterBonus * 0.1 + typeBonus).coerceIn(0.0, 1.0)

        return ObservationScore(
            targetName = "Target",
            score = total * 100.0,
            altitudeDeg = targetAltitudeDeg,
            moonSeparationDeg = 45.0,
            seeingScore = seeingScore.coerceIn(0.0, 1.0),
            hardwareFit = (filterBonus + typeBonus).coerceIn(0.0, 1.0),
        )
    }
}
