# Project Astra

Project Astra is a next-generation astronomy and astrophotography platform designed for ultra-fast sky navigation, scientific star catalogs, live hardware integration, and cinematic visualization.

## Architecture
- `app` — Android application shell and entry points.
- `ui` — Jetpack Compose screens, search, overlays, and night-vision operational HUD.
- `engine` — sky simulation, proper motion, catalog models, exoplanet transits, WDS hierarchies, and rendering-support math.
- `database` — Room persistence and local sky catalog access.
- `hardware` — telescope drivers, observatory profiles, ASCOM/INDI integration, and guided acquisition.

## Scientific engine
- Gaia DR3-inspired catalog indexing and proper motion calculations.
- WDS-ready binary and hierarchical multi-star modeling.
- Exoplanet transit simulation and habitable-zone estimation.
- Time-travel navigation for ±100,000-year astronomical simulation.

## Technology foundation
- Kotlin + Jetpack Compose
- Filament/Vulkan-ready rendering architecture
- SQLite/Room for local catalog caching and offline sky data
- Real-time hardware workflows for N.I.N.A., ASCOM/INDI, and PHD2

## Current implementation status
- Compose-based UI skeleton complete
- sky navigation and target math implemented
- exoplanet transit and WDS modeling primitives added
- observatory and mount integration scaffolding present
- database catalog repository abstraction added

## Getting started
1. Open the repository in Android Studio.
2. Sync Gradle and resolve Android dependencies.
3. Run the `app` configuration on an emulator or physical device.

## Next implementation goals
- Live starfield rendering with PSF and sub-pixel anti-aliasing
- Gaia DR3 streaming and indexing
- WDS hierarchical orbit overlays
- Exoplanet target panels and transit metrics
- Panorama-driven horizon masking and local obstruction modeling
