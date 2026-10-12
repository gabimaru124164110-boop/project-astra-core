package com.projectastra.engine

import kotlin.math.*

data class SkyTarget(
    val name: String,
    val type: String,
    val rightAscensionDeg: Double,
    val declinationDeg: Double,
    val magnitude: Double,
    val distanceLy: Double = 0.0,
)

class SkyMath {
    fun localSiderealTimeHours(longitudeDeg: Double, utcHours: Double): Double {
        val gmstHours = (18.697374558 + 24.06570982441908 * (utcHours / 24.0)) % 24.0
        return (gmstHours + longitudeDeg / 15.0 + 24.0) % 24.0
    }

    fun horizontalCoordinates(
        rightAscensionDeg: Double,
        declinationDeg: Double,
        latitudeDeg: Double,
        localSiderealTimeHours: Double,
    ): Pair<Double, Double> {
        val ra = Math.toRadians(rightAscensionDeg)
        val dec = Math.toRadians(declinationDeg)
        val lat = Math.toRadians(latitudeDeg)
        val lst = Math.toRadians(localSiderealTimeHours * 15.0)

        val altitude = sin(dec) * sin(lat) + cos(dec) * cos(lat) * cos(ra - lst)
        val azimuth = atan2(
            sin(ra - lst),
            cos(ra - lst) * sin(lat) - tan(dec) * cos(lat),
        )

        val altitudeDeg = Math.toDegrees(asin(altitude.coerceIn(-1.0, 1.0)))
        val azimuthDeg = (Math.toDegrees(azimuth) + 360.0) % 360.0

        return altitudeDeg to azimuthDeg
    }

    fun angularSeparation(
        ra1Deg: Double,
        dec1Deg: Double,
        ra2Deg: Double,
        dec2Deg: Double,
    ): Double {
        val dRa = Math.toRadians(ra2Deg - ra1Deg)
        val dDec = Math.toRadians(dec2Deg - dec1Deg)

        val a = sin(dDec / 2.0) * sin(dDec / 2.0) +
            cos(Math.toRadians(dec1Deg)) * cos(Math.toRadians(dec2Deg)) *
            sin(dRa / 2.0) * sin(dRa / 2.0)

        return 2.0 * atan2(sqrt(a), sqrt(1.0 - a))
    }

    fun brightnessFromMagnitude(magnitude: Double): Float {
        val normalized = (10.0 - magnitude.coerceIn(0.0, 10.0)) / 10.0
        return normalized.coerceIn(0.0, 1.0).toFloat()
    }
}
