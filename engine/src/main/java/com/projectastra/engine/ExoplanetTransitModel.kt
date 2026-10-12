package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class ExoplanetTransitModel {
    data class TransitParameters(
        val periodDays: Double,
        val transitDurationHours: Double,
        val impactParameter: Double,
        val depth: Double,
        val hostTemperatureK: Double,
    )

    fun fluxAtPhase(phase: Double, params: TransitParameters): Double {
        val normalized = ((phase % params.periodDays) / params.periodDays) * 2.0 * PI
        val transitSig = params.transitDurationHours / 24.0 / params.periodDays * PI * 3.0
        val gaussian = kotlin.math.exp(-0.5 * ((normalized - PI) / transitSig) * ((normalized - PI) / transitSig))
        return (1.0 - params.depth * gaussian).coerceIn(0.0, 1.0)
    }

    fun habitableZoneAU(stellarLuminosity: Double): Double = sqrt(stellarLuminosity)

    fun transitDepthForRadius(radiusRatio: Double): Double = (radiusRatio * radiusRatio) * 0.01
}
