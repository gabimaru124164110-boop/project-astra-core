# Native and renderer notes for Project Astra

This directory outlines how to build the native renderer and JNI bridge for the Filament/Vulkan (Android) and Metal (iOS/Mac) backends used by Project Astra.

1. Native library name: `astra_renderer` (loaded via System.loadLibrary("astra_renderer")).
2. Recommended structure:
   - native/
     - cpp/
       - src/
         - renderer.cpp (JNI entrypoints)
         - filament_bridge.cpp (Filament, Vulkan, Metal glue)
         - shaders/ (SPIR-V or compiled shader assets)
       - CMakeLists.txt
3. Build targets:
   - Android: use the Android NDK + CMake to compile `astra_renderer` as a shared library.
   - Desktop: provide a desktop build using Filament's native desktop libs for rapid testing.
4. Filament and Vulkan:
   - Link against Filament's matc/filamat toolchain for correct material compilation.
   - Upload SPIR-V for Vulkan or use Metal shader equivalents.

Security note: the native renderer will require careful permissions management on mobile devices — camera, USB, network for device bridges — and should keep long-lived native threads under strict lifecycle control.
