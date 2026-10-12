#version 450

in vec3 vColor;

out vec4 fragColor;

void main() {
    float luminance = clamp(dot(vColor, vec3(0.299, 0.587, 0.114)), 0.0, 1.0);
    fragColor = vec4(vColor * (0.45 + luminance), 1.0);
}
