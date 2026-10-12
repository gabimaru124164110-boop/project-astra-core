package com.projectastra.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val TWO_PI = 2.0 * PI

data class CelestialLocation(
    val rightAscensionDeg: Double,
    val declinationDeg: Double,
)

data class ProperMotion(
    val raMasPerYear: Double,
    val decMasPerYear: Double,
)

class CelestialNavigation {
    fun apparentPosition(
        base: CelestialLocation,
        motion: ProperMotion,
        years: Double,
    ): CelestialLocation {
        val raShift = motion.raMasPerYear * years / 3600000.0
        val decShift = motion.decMasPerYear * years / 3600000.0
        return CelestialLocation(
            rightAscensionDeg = (base.rightAscensionDeg + raShift + 360.0) % 360.0,
            declinationDeg = (base.declinationDeg + decShift).coerceIn(-90.0, 90.0),
        )
    }

    fun horizontalCoordinates(
        location: CelestialLocation,
        latitudeDeg: Double,
        localSiderealHours: Double,
    ): Pair<Double, Double> {
        val lat = Math.toRadians(latitudeDeg)
        val ra = Math.toRadians(location.rightAscensionDeg)
        val dec = Math.toRadians(location.declinationDeg)
        val lst = Math.toRadians(localSiderealHours * 15.0)

        val altitude = sin(dec) * sin(lat) + cos(dec) * cos(lat) * cos(ra - lst)
        val azimuth = atan2(
            sin(ra - lst),
            cos(ra - lst) * sin(lat) - tan(dec) * cos(lat)
        )

        return Pair(Math.toDegrees(Math.asin(altitude)), Math.toDegrees(azimuth + TWO_PI) % 360.0)
    }
}
