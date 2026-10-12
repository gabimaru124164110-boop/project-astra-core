package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

class SkyEventSimulator {
    data class EventState(
        val moonPhase: Double,
        val seeingArcsec: Double,
        val atmosphericTransparency: Double,
        val cloudCover: Double,
    )

    fun simulateNightConditions(timeFraction: Double): EventState {
        val moon = (sin(timeFraction * 2.0 * PI) + 1.0) * 0.5
        val seeing = 1.2 + (1.0 - moon) * 1.2
        val transparency = 0.82 + (1.0 - cloudCoverFraction(timeFraction)) * 0.18
        return EventState(
            moonPhase = moon,
            seeingArcsec = seeing,
            atmosphericTransparency = transparency,
            cloudCover = cloudCoverFraction(timeFraction),
        )
    }

    private fun cloudCoverFraction(timeFraction: Double): Double {
        val wave = sin(timeFraction * 2.0 * PI)
        return ((wave + 1.0) / 2.0) * 0.35
    }
}
