package com.projectastra.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectastra.engine.SkyTarget

@Composable
fun SkyTargetPanel(target: SkyTarget, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = target.name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Type: ${target.type}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp),
        )
        Text(
            text = "RA: ${target.rightAscensionDeg}° • Dec: ${target.declinationDeg}°",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "Magnitude: ${target.magnitude} • Distance: ${target.distanceLy} ly",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
