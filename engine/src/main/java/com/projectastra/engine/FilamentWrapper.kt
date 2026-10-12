package com.projectastra.engine

/**
 * FilamentWrapper.kt
 *
 * High-level Kotlin wrapper that will own the native Filament/Vulkan/Metal rendering context.
 * This file intentionally contains only safe JVM/Kotlin scaffolding; native initialization and
 * actual renderer lifecycle must be implemented in C/C++ and exposed via JNI for production use.
 *
 * The goals of this wrapper:
 * - Provide a small, testable JVM API for the rendering layer so Compose/UI can orchestrate frames
 * - Allow a CPU-side fallback rendering path for unit tests and headless operation
 * - Define shader assets, PSF parameters, and frame budget heuristics
 */

class FilamentWrapper private constructor() {
    data class InitParams(
        val preferredFps: Int = 120,
        val enableVsync: Boolean = true,
    )

    data class StarPsfParams(
        val coreBrightness: Float = 0.85f,
        val radiusPx: Float = 1.6f,
        val gamma: Float = 1.8f,
        val exposureBoost: Float = 1.0f,
    )

    private var initialized = false
    private var nativeLoaded = false

    companion object {
        @Volatile
        private var instance: FilamentWrapper? = null

        fun get(): FilamentWrapper = instance ?: synchronized(this) {
            instance ?: FilamentWrapper().also { instance = it }
        }

        init {
            // Attempt to load native library; this will be optional for JVM-only usage
            try {
                System.loadLibrary("astra_renderer")
                // nativeLoaded = true // cannot assign here to instance field
            } catch (t: Throwable) {
                // Native library not available in this environment. We'll fall back to CPU-only.
            }
        }
    }

    fun initialize(params: InitParams = InitParams()): Boolean {
        if (initialized) return true
        // TODO: call native init when available: nativeInit(params)
        initialized = true
        return initialized
    }

    fun shutdown() {
        if (!initialized) return
        // TODO: call native shutdown when available: nativeShutdown()
        initialized = false
    }

    fun prepareStarPsf(params: StarPsfParams): StarPsfParams {
        // Apply sane clamping for runtime-safe params
        return params.copy(
            coreBrightness = params.coreBrightness.coerceIn(0.0f, 2.5f),
            radiusPx = params.radiusPx.coerceIn(0.25f, 10.0f),
            gamma = params.gamma.coerceIn(0.4f, 4.0f),
            exposureBoost = params.exposureBoost.coerceIn(0.1f, 8.0f),
        )
    }

    // Placeholder native signatures (to be implemented in C/C++)
    private external fun nativeInit(preferredFps: Int): Boolean
    private external fun nativeShutdown()
    private external fun nativeRenderFrame(timeMs: Long)
}
