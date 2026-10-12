#version 450

layout(location = 0) in vec3 aPosition; // world-space direction vector
layout(location = 1) in vec2 aUV;       // sprite UV (for point sprite quad)
layout(location = 2) in vec3 aColor;    // per-star color (RGB)

layout(location = 0) out vec2 vUV;
layout(location = 1) out vec3 vColor;

layout(set = 0, binding = 0) uniform Camera {
    mat4 uViewProjection;
} CameraUBO;

void main() {
    // For a point-sprite based starfield we put per-vertex position in clip-space by using the
    // direction vector (aPosition) and expanding with the UV to create a small screen-space quad.
    // Here we pass UV to fragment shader for sub-pixel PSF evaluation.
    vUV = aUV;
    vColor = aColor;

    // aPosition is assumed unit-length direction on the unit sphere; convert to world position
    vec4 worldPos = vec4(normalize(aPosition), 0.0);

    // Position the quad vertex in clip space using the view-projection matrix. In practice the
    // Filament/Vulkan pipeline will use point sprites and expand on the GPU; this is a generic
    // vertex form for portability.
    gl_Position = CameraUBO.uViewProjection * worldPos;

    // gl_PointSize can be used for point sprites; set a base value (actual size controlled by
    // per-star attributes or a uniform in a full implementation)
    gl_PointSize = 16.0;
}
