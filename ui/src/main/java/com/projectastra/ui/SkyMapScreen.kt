package com.projectastra.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectastra.engine.ExoplanetTransitModel
import com.projectastra.engine.HorizonMaskModel
import com.projectastra.engine.SkyTarget

@Composable
fun SkyMapScreen() {
    val viewModel = remember { SkyMapViewModel() }
    val state by viewModel.uiState
    var searchQuery by remember { mutableStateOf(state.searchQuery) }

    val target = SkyTarget(
        name = state.selectedObject,
        type = "Exoplanet Host",
        rightAscensionDeg = 101.287,
        declinationDeg = -16.716,
        magnitude = 1.46,
        distanceLy = 8.6,
    )

    val transit = remember {
        ExoplanetTransitModel.TransitParameters(
            periodDays = 3.4,
            transitDurationHours = 2.8,
            impactParameter = 0.6,
            depth = 0.015,
            hostTemperatureK = 5600.0,
        )
    }

    val horizon = remember {
        HorizonMaskModel().maskStrength(state.targetAltitude)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF040B16)),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0A1831)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Project Astra",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            viewModel.updateSearch(it)
                        },
                        singleLine = true,
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 40.dp)
                            .background(Color(0xFF112340), RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0B1D33)),
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = state.selectedObject,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = "Transit depth: ${(transit.depth * 100.0).format()}% • HZ: ${ExoplanetTransitModel().habitableZoneAU(1.2).format()} AU",
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(top = 8.dp),
                        )
                        Text(
                            text = "Horizon mask: ${(horizon * 100.0).toInt()}% • Seeing ${state.seeing}" ,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 6.dp),
                        )
                    }
                }
            }

            SkyTargetPanel(target = target)
        }
    }
}

private fun Double.format(): String = String.format("%.2f", this)
