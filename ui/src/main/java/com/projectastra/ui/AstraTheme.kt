package com.projectastra.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NightPalette = darkColorScheme(
    primary = Color(0xFF9CC9FF),
    secondary = Color(0xFF5DE5D2),
    tertiary = Color(0xFFB9A2FF),
    background = Color(0xFF020712),
    surface = Color(0xFF0B1730),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFE9F5FF),
    onSurface = Color(0xFFE9F5FF),
)

private val DayPalette = lightColorScheme(
    primary = Color(0xFF1D4ED8),
    secondary = Color(0xFF0F766E),
    background = Color(0xFFF8FAFC),
    surface = Color(0xFFF1F5F9),
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF0F172A),
)

@Composable
fun AstraTheme(content: @Composable () -> Unit) {
    val palette = NightPalette
    MaterialTheme(
        colorScheme = palette,
        content = content,
    )
}
