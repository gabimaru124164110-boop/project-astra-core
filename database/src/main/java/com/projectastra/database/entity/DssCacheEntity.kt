package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.pow

class RenderingPipeline {
    data class RenderConfig(
        val targetFps: Int = 120,
        val useNightMode: Boolean = true,
        val antiAliasing: Boolean = true,
        val maxStars: Int = 500_000,
    )

    data class CameraState(
        val yawDeg: Double,
        val pitchDeg: Double,
        val zoom: Double,
    )

    data class StarSample(
        val raDeg: Double,
        val decDeg: Double,
        val brightness: Double,
        val colorR: Float,
        val colorG: Float,
        val colorB: Float,
    )

    fun frameBudget(config: RenderConfig): Double {
        val pixelBudget = config.maxStars * 4.0
        val renderCost = pixelBudget / (config.targetFps * 1.0)
        return renderCost / 1000000.0
    }

    fun gaussianPsf(core: Double, radius: Double, x: Double, y: Double): Double {
        val sigma = radius.coerceAtLeast(0.2)
        val exponent = -(x * x + y * y) / (2.0 * sigma * sigma)
        return core * exp(exponent)
    }

    fun brightnessToColor(brightness: Double): Triple<Float, Float, Float> {
        val clamped = brightness.coerceIn(0.0, 1.0)
        val r = (0.45 + clamped * 0.55).toFloat()
        val g = (0.55 + clamped * 0.45).toFloat()
        val b = (0.8 + clamped * 0.2).toFloat()
        return Triple(r, g, b)
    }

    fun viewTransform(camera: CameraState): Double {
        val zoomFactor = camera.zoom.coerceIn(0.1, 100.0)
        return (zoomFactor * (1.0 + (camera.pitchDeg / 180.0) * 0.5)) / (PI)
    }
}
