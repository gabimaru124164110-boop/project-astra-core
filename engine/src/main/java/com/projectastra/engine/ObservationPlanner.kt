package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class ObservationPlanner {
    data class SessionPlan(
        val targetName: String,
        val altitudeDeg: Double,
        val durationMinutes: Int,
        val filter: String,
        val exposureSeconds: Int,
        val strategy: String,
    )

    fun optimizeSession(
        targetName: String,
        altitudeDeg: Double,
        seeingArcsec: Double,
        moonIlluminationPercent: Double,
        filterProfile: String,
    ): SessionPlan {
        val altitudeScore = altitudeDeg.coerceIn(0.0, 90.0)
        val seeingPenalty = seeingArcsec.coerceIn(0.8, 4.5)
        val moonPenalty = moonIlluminationPercent.coerceIn(0.0, 100.0)
        val duration = when {
            altitudeScore > 55.0 && seeingPenalty < 2.0 -> 180
            altitudeScore > 35.0 -> 120
            else -> 75
        }

        val filter = when {
            filterProfile.contains("narrowband", ignoreCase = true) -> "Ha/SII/OIII"
            filterProfile.contains("dual-band", ignoreCase = true) -> "Dual-Band"
            else -> "LRGB"
        }

        val exposure = when {
            seeingPenalty > 2.5 -> 120
            moonPenalty > 50.0 -> 90
            else -> 180
        }

        val strategy = if (altitudeScore > 60.0) "High-altitude capture" else "Offset-merge capture"

        return SessionPlan(
            targetName = targetName,
            altitudeDeg = altitudeScore,
            durationMinutes = duration,
            filter = filter,
            exposureSeconds = exposure,
            strategy = strategy,
        )
    }
}
