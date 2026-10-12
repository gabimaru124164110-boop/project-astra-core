package com.projectastra.engine

import kotlin.math.cos
import kotlin.math.sin

data class WdsComponent(
    val designation: String,
    val separationArcsec: Double,
    val positionAngleDeg: Double,
    val magnitude: Double,
    val primary: Boolean = false,
)

data class WdsSystem(
    val identifier: String,
    val primaryName: String,
    val components: List<WdsComponent>,
)

class WdsModel {
    fun projectedSeparation(component: WdsComponent): Pair<Double, Double> {
        val theta = Math.toRadians(component.positionAngleDeg)
        val x = component.separationArcsec * cos(theta)
        val y = component.separationArcsec * sin(theta)
        return Pair(x, y)
    }

    fun meanMagnitude(system: WdsSystem): Double =
        system.components.map { it.magnitude }.average()
}
