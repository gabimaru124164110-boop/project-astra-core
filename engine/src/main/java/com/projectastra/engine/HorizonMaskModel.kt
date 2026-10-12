package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class HorizonMaskModel {
    data class Obstruction(
        val azimuthDeg: Double,
        val altitudeDeg: Double,
        val heightM: Double,
    )

    fun isVisible(targetAzimuthDeg: Double, targetAltitudeDeg: Double, obstructions: List<Obstruction>): Boolean {
        val closeObstruction = obstructions.any { obstruction ->
            val azDelta = abs((targetAzimuthDeg - obstruction.azimuthDeg + 180.0) % 360.0 - 180.0)
            val visibleWindow = (targetAltitudeDeg >= obstruction.altitudeDeg)
            azDelta < 12.0 && visibleWindow
        }
        return !closeObstruction
    }

    fun maskStrength(targetAltitudeDeg: Double): Double = (1.0 - targetAltitudeDeg / 90.0).coerceIn(0.0, 1.0)
}
