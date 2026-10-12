# Project Astra

Project Astra is a next-generation astronomy and astrophotography platform designed for ultra-fast sky navigation, scientific star catalogs, live hardware integration, and cinematic visualization.

## New: Rendering core (experimental)

This commit adds an experimental rendering core scaffolding and PSF-based star shaders:

- GLSL vertex and fragment shaders implementing a PSF-driven point-sprite renderer (engine/src/main/shaders/*)
- FilamentWrapper.kt — Kotlin-side wrapper outlining native renderer lifecycle and JNI signatures
- StarPsf.kt — CPU PSF utilities for testing and fallback rendering
- native/README.md — guide for building the `astra_renderer` native library (Filament/Vulkan/Metal)

## Next steps for production renderer

1. Implement native JNI layer in `native/cpp` that initializes Filament engine and exposes `nativeInit`, `nativeShutdown`, and `nativeRenderFrame`.
2. Compile shader assets with Filament's `matc` to create optimized materials.
3. Implement efficient GPU point-sprite batching for millions of stars (vertex texture buffering or SSBOs).
4. Add multi-resolution streaming of Gaia DR3 tiles with LOD, index-by-magnitude, and GPU-driven culling.
