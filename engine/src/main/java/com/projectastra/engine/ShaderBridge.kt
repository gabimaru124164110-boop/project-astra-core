package com.projectastra.engine

import kotlin.math.PI

class ShaderBridge {
    data class ShaderProgram(
        val vertexShaderSource: String,
        val fragmentShaderSource: String,
    )

    fun createStarfieldShaders(): ShaderProgram {
        val vertex = """
            #version 450
            layout(location = 0) in vec3 aPosition;
            layout(location = 1) in vec3 aColor;
            uniform mat4 uViewProjection;
            out vec3 vColor;
            void main() {
                vColor = aColor;
                gl_Position = uViewProjection * vec4(aPosition, 1.0);
            }
        """.trimIndent()

        val fragment = """
            #version 450
            in vec3 vColor;
            out vec4 fragColor;
            void main() {
                float luminance = clamp(dot(vColor, vec3(0.299, 0.587, 0.114)), 0.0, 1.0);
                fragColor = vec4(vColor * (0.55 + luminance), 1.0);
            }
        """.trimIndent()

        return ShaderProgram(vertex, fragment)
    }

    fun frameBudget(targetFps: Int, starCount: Int): Double = starCount.toDouble() / targetFps.toDouble()
}
