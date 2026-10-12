package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class DoubleStarComponent(
    val designation: String,
    val separationArcsec: Double,
    val positionAngleDeg: Double,
    val magnitude: Double,
    val primary: Boolean = false,
)

data class DoubleStarSystem(
    val designation: String,
    val primaryDesignation: String,
    val components: List<DoubleStarComponent>,
)

class DoubleStarHierarchy {
    fun projectedOffset(component: DoubleStarComponent): Pair<Double, Double> {
        val theta = Math.toRadians(component.positionAngleDeg)
        val x = component.separationArcsec * cos(theta)
        val y = component.separationArcsec * sin(theta)
        return x to y
    }

    fun meanMagnitude(system: DoubleStarSystem): Double =
        system.components.map { it.magnitude }.average()

    fun splitSeparation(system: DoubleStarSystem): Double =
        system.components.maxOfOrNull { it.separationArcsec } ?: 0.0
}
