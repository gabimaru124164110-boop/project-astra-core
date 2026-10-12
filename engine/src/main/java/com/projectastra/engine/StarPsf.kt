package com.projectastra.engine

import kotlin.math.exp
import kotlin.math.max
import kotlin.math.pow

/**
 * StarPsf.kt
 * CPU-side PSF utilities used for testing and as a fallback renderer.
 */
object StarPsf {
    fun gaussian(r: Double, sigma: Double): Double {
        val s2 = sigma * sigma
        return kotlin.math.exp(-0.5 * (r * r) / s2)
    }

    fun samplePsfIntensity(coreBrightness: Double, radiusPx: Double, x: Double, y: Double): Double {
        val r = kotlin.math.sqrt(x * x + y * y)
        val i = coreBrightness * gaussian(r, radiusPx)
        return max(0.0, i)
    }

    fun toneMap(linear: Double, gamma: Double): Double {
        return linear.pow(1.0 / (gamma.coerceAtLeast(0.1)))
    }
}
