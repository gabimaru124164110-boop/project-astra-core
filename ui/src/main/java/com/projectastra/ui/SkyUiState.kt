package com.projectastra.ui

import androidx.compose.runtime.Stable

@Stable
data class SkyUiState(
    val selectedObject: String = "Sirius",
    val hourAngle: Double = 18.5,
    val searchQuery: String = "",
    val localTime: String = "22:14",
    val latitude: Double = 35.6,
    val longitude: Double = -117.9,
    val targetAltitude: Double = 62.4,
    val catalogMode: String = "Gaia DR3 / WDS / DSS",
    val hardwareMode: String = "ASCOM / INDI / PHD2",
)
