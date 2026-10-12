#version 450

layout(location = 0) in vec3 aPosition;
layout(location = 1) in vec3 aColor;

out vec3 vColor;

uniform mat4 uViewProjection;

void main() {
    vColor = aColor;
    gl_Position = uViewProjection * vec4(aPosition, 1.0);
}
