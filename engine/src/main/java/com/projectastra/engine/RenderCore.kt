package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class RenderCore {
    data class Vertex(
        val x: Float,
        val y: Float,
        val z: Float,
        val r: Float,
        val g: Float,
        val b: Float,
    )

    data class Uniforms(
        val viewProjectionMatrix: FloatArray,
        val time: Float,
        val starScale: Float,
    )

    fun starfieldVertex(
        raDeg: Double,
        decDeg: Double,
        magnitude: Double,
        brightness: Float,
    ): Vertex {
        val ra = Math.toRadians(raDeg)
        val dec = Math.toRadians(decDeg)
        val x = (cos(dec) * cos(ra)).toFloat() * (1.0f + brightness)
        val y = (cos(dec) * sin(ra)).toFloat() * (1.0f + brightness)
        val z = (sin(dec)).toFloat()
        val color = when {
            magnitude < 0.0 -> 1.0f
            magnitude < 2.0 -> 0.9f
            magnitude < 4.0 -> 0.75f
            else -> 0.5f
        }
        return Vertex(x, y, z, color, color, 1.0f)
    }

    fun frameBudget(maxStars: Int, targetFps: Int): Double = maxStars.toDouble() / targetFps.toDouble()
}
