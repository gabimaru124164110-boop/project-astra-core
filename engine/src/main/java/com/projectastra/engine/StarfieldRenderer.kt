package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

class StarfieldRenderer {
    fun brightnessFromMagnitude(magnitude: Double): Float {
        val normalized = (10.0 - magnitude.coerceIn(0.0, 10.0)) / 10.0
        return normalized.coerceIn(0.0, 1.0).toFloat()
    }

    fun psfRadiusForMagnitude(magnitude: Double): Float {
        val radius = 2.4f - magnitude.coerceIn(-2.0, 10.0).toFloat() * 0.12f
        return radius.coerceIn(0.5f, 3.0f)
    }

    fun stellarColorFromBvIndex(bvIndex: Double): Triple<Float, Float, Float> {
        val normalized = bvIndex.coerceIn(-0.5, 2.0)
        val r = (0.5 + normalized / 2.0).coerceIn(0.0, 1.0).toFloat()
        val g = (0.75 + (1.0 - abs(normalized - 0.8)) * 0.3).coerceIn(0.0, 1.0).toFloat()
        val b = (1.0 - normalized / 2.5).coerceIn(0.0, 1.0).toFloat()
        return Triple(r, g, b)
    }

    fun stellarProfile(coreBrightness: Double, radialDistance: Double): Double {
        val sigma = 0.45
        val gaussian = exp(-0.5 * (radialDistance * radialDistance) / (sigma * sigma))
        return (coreBrightness * gaussian).coerceIn(0.0, 1.0)
    }

    fun transitDepth(phase: Double, periodDays: Double, transitDepth: Double): Double {
        val normalized = (phase / periodDays) % 1.0
        val angle = normalized * 2.0 * PI
        val gaussian = exp(-0.5 * (angle / (PI / 16.0)) * (angle / (PI / 16.0)))
        return (transitDepth * gaussian).coerceIn(0.0, 1.0)
    }
}
