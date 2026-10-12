# Project Astra

Project Astra is a next-generation astronomy and astrophotography platform designed for ultra-fast sky navigation, scientific star catalogs, live hardware integration, and cinematic visualization.

## Goals
- Real-time sky simulation with star catalogs, proper motion, and time-lapse traversal.
- High-quality rendering using Filament/Vulkan-native architecture and Compose-driven controls.
- Offline-first database storage for local bright-star and DSS indexing.
- Native hardware support for mount control, guiding, and acquisition workflows.

## Modules
- `app`: Android application shell and entry point.
- `ui`: Compose-based night-mode interface and interaction layer.
- `engine`: astrophysics simulation, rendering model, and starfield logic.
- `database`: Room schema and local catalog access.
- `hardware`: abstraction layer for ASCOM, INDI, N.I.N.A., and guiding workflows.

## Getting started
1. Open the project in Android Studio.
2. Sync Gradle.
3. Run the `app` target on a device or emulator.

## Roadmap
- Gaia DR3 streaming and indexing
- WDS multi-star hierarchy modeling
- Exoplanet transit engine and habitable-zone overlays
- Hardware control integration and framing calibration
- Advanced procedural horizon mapping and weather overlays
