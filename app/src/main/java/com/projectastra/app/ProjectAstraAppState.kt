package com.projectastra.app

import com.projectastra.engine.SkyTarget

data class ProjectAstraAppState(
    val activeTarget: SkyTarget = SkyTarget(
        name = "Sirius",
        type = "Star",
        rightAscensionDeg = 101.287,
        declinationDeg = -16.716,
        magnitude = -1.46,
        distanceLy = 8.6,
    ),
    val nightModeEnabled: Boolean = true,
    val liveSessionConnected: Boolean = false,
    val targetRecommendationScore: Double = 91.5,
)
