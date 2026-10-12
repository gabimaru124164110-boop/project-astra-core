package com.projectastra.hardware

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

data class ObservatoryProfile(
    val latitudeDeg: Double,
    val longitudeDeg: Double,
    val elevationM: Double,
    val timeZoneOffsetHours: Double,
)

class ObservatoryController(
    private val profile: ObservatoryProfile,
) {
    fun localSiderealTime(julianDate: Double): Double {
        val gmst = (18.697374558 + 24.06570982441908 * julianDate) % 24.0
        val local = gmst + profile.longitudeDeg / 15.0 + profile.timeZoneOffsetHours
        return ((local + 24.0) % 24.0)
    }

    fun altitudeForTarget(raDeg: Double, decDeg: Double, currentLstHours: Double): Double {
        val lat = Math.toRadians(profile.latitudeDeg)
        val lst = Math.toRadians(currentLstHours * 15.0)
        val dec = Math.toRadians(decDeg)
        val ra = Math.toRadians(raDeg)

        val altitude = sin(dec) * sin(lat) + cos(dec) * cos(lat) * cos(ra - lst)
        return Math.toDegrees(Math.asin(altitude))
    }
}
