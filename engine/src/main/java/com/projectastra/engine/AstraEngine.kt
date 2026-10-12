package com.projectastra.engine

import kotlin.math.roundToInt

class AstraEngine {
    fun formattedSkyStatus(offsetX: Float, offsetY: Float): String {
        val ra = (offsetX / 10f).coerceIn(-180f, 180f)
        val dec = (offsetY / 10f).coerceIn(-90f, 90f)

        return buildString {
            append("Current view\n")
            append("RA: ${ra.roundToInt()}°\n")
            append("Dec: ${dec.roundToInt()}°\n")
            append("Catalogs: Gaia DR3 / WDS / DSS")
        }
    }
}
