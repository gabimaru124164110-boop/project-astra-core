package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class TemporalSkyEngine {
    data class TemporalState(
        val julianDay: Double,
        val yearsOffset: Double,
        val precessionDeg: Double,
        val nutationDeg: Double,
    )

    fun applyTemporalTransform(
        rightAscensionDeg: Double,
        declinationDeg: Double,
        yearsOffset: Double,
    ): Pair<Double, Double> {
        val precession = yearsOffset * 0.0001
        val nutation = yearsOffset * 0.00003

        val newRa = (rightAscensionDeg + precession * 15.0 + nutation * 10.0 + 360.0) % 360.0
        val newDec = (declinationDeg + nutation * 12.0).coerceIn(-90.0, 90.0)
        return newRa to newDec
    }

    fun generateTimeTravelPath(
        startRaDeg: Double,
        startDecDeg: Double,
        yearsWindow: Double,
        steps: Int,
    ): List<Pair<Double, Double>> {
        val stepSize = yearsWindow / steps
        return (0..steps).map { index ->
            val years = (index * stepSize) - (yearsWindow / 2.0)
            applyTemporalTransform(startRaDeg, startDecDeg, years)
        }
    }

    fun horizontalCoordinates(
        rightAscensionDeg: Double,
        declinationDeg: Double,
        latitudeDeg: Double,
        localSiderealHours: Double,
    ): Pair<Double, Double> {
        val ra = Math.toRadians(rightAscensionDeg)
        val dec = Math.toRadians(declinationDeg)
        val lat = Math.toRadians(latitudeDeg)
        val lst = Math.toRadians(localSiderealHours * 15.0)

        val altitude = sin(dec) * sin(lat) + cos(dec) * cos(lat) * cos(ra - lst)
        val azimuth = kotlin.math.atan2(
            sin(ra - lst),
            cos(ra - lst) * sin(lat) - tan(dec) * cos(lat),
        )

        val altitudeDeg = Math.toDegrees(kotlin.math.asin(altitude.coerceIn(-1.0, 1.0)))
        val azimuthDeg = (Math.toDegrees(azimuth) + 360.0) % 360.0
        return altitudeDeg to azimuthDeg
    }
}
