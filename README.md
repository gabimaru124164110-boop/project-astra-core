# Project Astra

Project Astra is a next-generation astronomy and astrophotography platform designed for ultra-fast sky navigation, scientific star catalogs, live hardware integration, and cinematic visualization.

## Architecture
- `app` — Android app shell and app state orchestration.
- `ui` — Compose screens, target panels, search, recommendation results, and observational metadata.
- `engine` — astronomy math, rendering pipeline abstraction, shader bridge, search, recommendation, observation planning, Gaia indexing, WDS hierarchy, exoplanet transit modeling, and horizon masking.
- `database` — Room persistence, catalog search, local cache layer, and WDS support.
- `hardware` — telescope drivers, mount profiles, and acquisition devices.

## Scientific core
- Gaia DR3-inspired catalog indexing and proper motion calculations.
- WDS-ready binary and hierarchical multi-star modeling with split separation and position angle support.
- Exoplanet transit simulation and habitable-zone estimation.
- Time-travel navigation for ±100,000-year astronomical simulation.
- Universal search and target discovery across stars, deep-sky objects, exoplanets, and transient phenomena.
- Local horizon obstruction and visibility masking for realistic observing windows.

## Render and acquisition stack
- Filament-like render abstraction for 120Hz starfield work.
- Night-vision-friendly scene rendering and optimization.
- Session planning, exposure balancing, and filter-aware capture logic.
- Hardware bridge for N.I.N.A., ASCOM, INDI, and PHD2 workflows.

## Current implementation status
- Compose-based UI skeleton complete.
- target recommendation engine added.
- universal search layer added.
- render pipeline and shader bridge added.
- observation planner added.
- Gaia catalog indexing layer and double-star hierarchy support added.
- exoplanet transit modeling and horizon masking added.
- central app state and target panel implemented.
- catalog repository and Room schema extended for search and metadata.

## Getting started
1. Open the repo in Android Studio.
2. Sync Gradle and resolve Android dependencies.
3. Run the `app` configuration on an emulator or physical device.

## Next implementation goals
- live starfield rendering with PSF and sub-pixel anti-aliasing.
- Gaia DR3 streaming and indexing against real catalog formats.
- WDS hierarchical orbit overlays and binary-star render layers.
- exoplanet target panels and transit metrics.
- panorama-driven horizon masking and local obstruction modeling.
