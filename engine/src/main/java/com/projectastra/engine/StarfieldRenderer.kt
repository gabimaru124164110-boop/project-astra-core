package com.projectastra.engine

import kotlin.math.sin

class StarfieldRenderer {
    fun stellarBrightness(index: Int, timeScale: Float): Float {
        val base = (index % 128).toFloat() / 128f
        return (base * 0.8f + sin(timeScale + index * 0.13f) * 0.25f).coerceIn(0f, 1f)
    }
}
