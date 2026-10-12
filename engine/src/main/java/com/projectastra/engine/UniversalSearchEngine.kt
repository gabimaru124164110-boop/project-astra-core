package com.projectastra.engine

import java.util.Locale
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class SkySearchResult(
    val name: String,
    val type: String,
    val raDeg: Double,
    val decDeg: Double,
    val magnitude: Double,
    val score: Double,
)

class UniversalSearchEngine {
    private val knownCatalog = listOf(
        "Sirius", "Betelgeuse", "Rigel", "Andromeda", "M31", "NGC 253",
        "PSR B0531+21", "Cygnus X-1", "M87", "Comet C/2024 A1", "Europa",
        "Kepler-186f", "T Tauri", "NGC 7000", "ATLAS J2023"
    )

    fun search(query: String): List<SkySearchResult> {
        val normalized = query.trim().lowercase(Locale.getDefault())
        if (normalized.isEmpty()) return emptyList()

        return knownCatalog.filter { it.lowercase(Locale.getDefault()).contains(normalized) }
            .mapIndexed { index, name ->
                SkySearchResult(
                    name = name,
                    type = when {
                        name.contains("PSR") -> "Pulsar"
                        name.contains("NGC") || name.contains("M") -> "Nebula/Cluster"
                        name.contains("Kepler") -> "Exoplanet"
                        name.contains("Comet") -> "Comet"
                        name.contains("Europa") -> "Moon"
                        name.contains("Cygnus") -> "Black Hole Candidate"
                        else -> "Star"
                    },
                    raDeg = 15.0 * ((index + 1) * 7.5),
                    decDeg = (-45.0 + (index % 10) * 10.0),
                    magnitude = (index % 8).toDouble() / 2.0,
                    score = 90.0 - index * 4.0,
                )
            }
    }

    fun classifyTarget(name: String): String = when {
        name.contains("PSR") -> "Pulsar"
        name.contains("Kepler") -> "Exoplanet"
        name.contains("M") || name.contains("NGC") -> "Deep Sky Object"
        name.contains("Comet") -> "Comet"
        name.contains("Cygnus") || name.contains("M87") -> "Compact Object"
        else -> "Star"
    }
}
