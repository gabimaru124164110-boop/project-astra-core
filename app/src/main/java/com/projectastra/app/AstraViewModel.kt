package com.projectastra.app

import androidx.lifecycle.ViewModel
import com.projectastra.engine.ObservationPlanner
import com.projectastra.engine.ProjectAstraAppState

class AstraViewModel : ViewModel() {
    private val planner = ObservationPlanner()
    val appState = ProjectAstraAppState()

    fun generatePlan(targetName: String): ObservationPlanner.SessionPlan = planner.optimizeSession(
        targetName = targetName,
        altitudeDeg = appState.activeTarget.rightAscensionDeg,
        seeingArcsec = 1.8,
        moonIlluminationPercent = 18.0,
        filterProfile = "dual-band narrowband",
    )
}
