package com.projectastra.app

import com.projectastra.engine.DoubleStarSystem
import com.projectastra.engine.GaiaCatalogIndex
import com.projectastra.engine.GaiaStarRecord

class AstraSessionCatalog {
    val gaiaIndex = GaiaCatalogIndex()

    fun seedGaiaDemoCatalog() {
        gaiaIndex.load(
            listOf(
                GaiaStarRecord(1, "HIP 32349", 61.2, 1.4, 0.13, 0.0, 0.0, 0.0),
                GaiaStarRecord(2, "HIP 54547", 72.7, -16.7, 1.46, 0.0, 0.0, 0.0),
                GaiaStarRecord(3, "HIP 54061", 77.5, -5.7, 2.8, 0.0, 0.0, 0.0),
                GaiaStarRecord(4, "HIP 20205", 94.8, 15.2, 3.2, 0.0, 0.0, 0.0),
            )
        )
    }

    fun demoDoubleStar(): DoubleStarSystem = DoubleStarSystem(
        designation = "STF 227",
        primaryDesignation = "HIP 32349",
        components = listOf(
            com.projectastra.engine.DoubleStarComponent("HIP 32349 A", 4.2, 142.0, 0.13, true),
            com.projectastra.engine.DoubleStarComponent("HIP 32349 B", 4.2, 322.0, 2.8, false),
        ),
    )
}
