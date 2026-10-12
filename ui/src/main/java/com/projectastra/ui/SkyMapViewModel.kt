package com.projectastra.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.projectastra.engine.SkyTarget

class SkyMapViewModel : ViewModel() {
    var uiState: MutableState<SkyUiState> = mutableStateOf(SkyUiState())
        private set

    fun updateSearch(query: String) {
        uiState.value = uiState.value.copy(searchQuery = query)
    }

    fun selectTarget(target: SkyTarget) {
        uiState.value = uiState.value.copy(
            selectedObject = target.name,
            targetAltitude = 65.0,
            catalogMode = "Gaia DR3 • WDS • DSS",
            hardwareMode = "ASCOM • INDI • PHD2",
        )
    }

    fun updateTime(time: String) {
        uiState.value = uiState.value.copy(localTime = time)
    }
}
