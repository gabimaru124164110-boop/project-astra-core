package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class FilamentRenderer {
    data class RenderState(
        val targetFps: Int = 120,
        val useNightVision: Boolean = true,
        val starScale: Float = 1.0f,
        val brightnessBoost: Float = 1.0f,
    )

    data class RenderFrame(
        val starCount: Int,
        val frameBudgetMs: Double,
        val renderMode: String,
    )

    fun prepareFrame(state: RenderState): RenderFrame {
        val budget = (1000.0 / state.targetFps.toDouble()).coerceAtLeast(8.0)
        return RenderFrame(
            starCount = 250_000,
            frameBudgetMs = budget,
            renderMode = if (state.useNightVision) "night-vision" else "standard",
        )
    }

    fun pointSpriteRadius(magnitude: Double): Float {
        val radius = (4.0 - magnitude.coerceIn(-1.0, 10.0)) * 0.23f
        return radius.coerceIn(0.25f, 2.5f)
    }

    fun starColorFromBv(bvIndex: Double): Triple<Float, Float, Float> {
        val normalized = bvIndex.coerceIn(-0.5, 2.0)
        val r = (0.45 + normalized / 2.0).coerceIn(0.0, 1.0).toFloat()
        val g = (0.6 + (1.0 - kotlin.math.abs(normalized - 0.7)) * 0.35).coerceIn(0.0, 1.0).toFloat()
        val b = (0.85 - normalized / 3.0).coerceIn(0.0, 1.0).toFloat()
        return Triple(r, g, b)
    }

    fun projectionMatrix(fovDeg: Double, aspect: Double, near: Double = 0.1, far: Double = 1000.0): Double {
        val fov = Math.toRadians(fovDeg)
        val f = 1.0 / kotlin.math.tan(fov / 2.0)
        return (f / aspect) * (far / (far - near))
    }
}
