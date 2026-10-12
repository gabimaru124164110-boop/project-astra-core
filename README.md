package com.projectastra.hardware

interface AstroDevice {
    val name: String
    fun initialize(): Boolean
    fun shutdown(): Boolean
}

class NINADriver : AstroDevice {
    override val name: String = "N.I.N.A."
    override fun initialize(): Boolean = true
    override fun shutdown(): Boolean = true
}

class AscomDriver : AstroDevice {
    override val name: String = "ASCOM"
    override fun initialize(): Boolean = true
    override fun shutdown(): Boolean = true
}

class IndiDriver : AstroDevice {
    override val name: String = "INDI"
    override fun initialize(): Boolean = true
    override fun shutdown(): Boolean = true
}

class PHD2Driver : AstroDevice {
    override val name: String = "PHD2"
    override fun initialize(): Boolean = true
    override fun shutdown(): Boolean = true
}

class AstroDeviceManager {
    private val devices: List<AstroDevice> = listOf(
        NINADriver(),
        AscomDriver(),
        IndiDriver(),
        PHD2Driver(),
    )

    fun connectedDevices(): List<String> = devices.map { it.name }

    fun initializeAll(): Boolean = devices.all { it.initialize() }

    fun shutdownAll(): Boolean = devices.all { it.shutdown() }
}
