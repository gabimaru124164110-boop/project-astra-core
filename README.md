# Project Astra

Project Astra is a next-generation astronomy and astrophotography platform designed for ultra-fast sky navigation, scientific star catalogs, live hardware integration, and cinematic visualization.

## Architecture
- `app` — Android app shell and app state orchestration.
- `ui` — Compose screens, target panels, search, recommendation results, and observing metadata.
- `engine` — astronomy math, rendering pipeline abstraction, shader bridge, universal search, and recommendation engine.
- `database` — Room persistence and catalog search/repository layer.
- `hardware` — telescope drivers, mount profiles, and acquisition devices.

## Scientific engine
- Gaia DR3-inspired catalog indexing and proper motion calculations.
- WDS-ready binary and hierarchical multi-star modeling.
- Exoplanet transit simulation and habitable-zone estimation.
- Time-travel navigation for ±100,000-year astronomical simulation.
- Universal search and target discovery across stars, deep-sky objects, exoplanets, and transient phenomena.

## Technology foundation
- Kotlin + Jetpack Compose
- Filament/Vulkan-ready rendering architecture
- SQLite/Room for local catalog caching and offline sky data
- Real-time hardware workflows for N.I.N.A., ASCOM/INDI, and PHD2

## Current implementation status
- Compose-based UI skeleton complete
- target recommendation engine added
- universal search layer added
- rendering pipeline and shader bridge added
- central app state and target panel implemented
- catalog repository and Room schema extended for search and metadata

## Getting started
1. Open the repository in Android Studio.
2. Sync Gradle and resolve Android dependencies.
3. Run the `app` configuration on an emulator or physical device.

## Next implementation goals
- live starfield rendering with PSF and sub-pixel anti-aliasing
- Gaia DR3 streaming and indexing
- WDS hierarchical orbit overlays
- exoplanet target panels and transit metrics
- panorama-driven horizon masking and local obstruction modeling
