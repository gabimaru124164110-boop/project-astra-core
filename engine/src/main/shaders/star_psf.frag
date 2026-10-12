#version 450

layout(location = 0) in vec2 vUV;
layout(location = 1) in vec3 vColor;

layout(location = 0) out vec4 outColor;

// Uniforms for PSF control
layout(set = 0, binding = 1) uniform PSFParams {
    float coreBrightness;    // 0..1
    float radiusPx;          // PSF standard deviation in pixels
    float gamma;             // tonal mapping
    float exposureBoost;
} PSF;

// Sub-pixel anti-aliasing via analytic integration of gaussian over the pixel area is expensive.
// We approximate by evaluating the gaussian at multiple sample points (2x2) inside the pixel and
// averaging — a good trade-off for high-performance star rendering.

vec3 bvToRgb(float bv) {
    // Simple approximate mapping of B-V index to RGB color curve
    float t = clamp((bv + 0.5) / 2.5, 0.0, 1.0);
    // warm (reddish) to cool (bluish)
    float r = mix(1.0, 0.6, t);
    float g = mix(0.95, 0.8, t);
    float b = mix(0.8, 1.0, t);
    return vec3(r, g, b);
}

float gaussian(float r, float sigma) {
    float s2 = sigma * sigma;
    return exp(-0.5 * (r * r) / s2);
}

void main() {
    // vUV in [-1,1] across the point sprite quad, assume center at (0,0)
    // Map UV to pixel-space radius by multiplying with radiusPx
    vec2 pixelPos = vUV * PSF.radiusPx;

    // 2x2 sub-sample grid inside the pixel for smoother edges
    float total = 0.0;
    const int N = 4;
    const vec2 samples[4] = vec2[4](
        vec2(-0.25, -0.25),
        vec2(0.25, -0.25),
        vec2(-0.25, 0.25),
        vec2(0.25, 0.25)
    );

    for (int i = 0; i < N; ++i) {
        vec2 sPos = pixelPos + samples[i];
        float r = length(sPos);
        total += gaussian(r, PSF.radiusPx);
    }

    float psf = total / float(N);

    // coreBrightness lifts the central intensity (emulates photographic core/halo)
    float intensity = PSF.coreBrightness * psf * PSF.exposureBoost;

    // Tone mapping / gamma
    intensity = pow(intensity, 1.0 / max(0.1, PSF.gamma));

    vec3 color = vColor * intensity;

    // Output HDR-like alpha for additive blending (premultiplied alpha not assumed)
    outColor = vec4(color, clamp(intensity, 0.0, 1.0));

    // Discard extremely dim fragments for performance
    if (outColor.a < 0.002) {
        discard;
    }
}
