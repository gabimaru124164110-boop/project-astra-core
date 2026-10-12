package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

data class ExoplanetTransit(
    val periodDays: Double,
    val transitDepth: Double,
    val impactParameter: Double,
    val hostStarRadiusSolar: Double,
)

class ExoplanetTransitEngine {
    fun transitCurve(
        epoch: Double,
        phase: Double,
        transit: ExoplanetTransit,
    ): Double {
        val normalized = ((phase - epoch) / transit.periodDays) % 1.0
        val angle = normalized * 2.0 * PI
        val gaussian = exp(-0.5 * ((angle / (PI / 18.0)) * (angle / (PI / 18.0))))
        val eclipse = transit.transitDepth * gaussian
        return (1.0 - eclipse).coerceIn(0.0, 1.0)
    }

    fun habitableZoneBoundary(temperatureK: Double): Double {
        val luminosityRatio = (temperatureK / 5778.0) * (temperatureK / 5778.0)
        return 1.0 * kotlin.math.sqrt(luminosityRatio)
    }

    private fun exp(value: Double): Double = kotlin.math.exp(value)
}
