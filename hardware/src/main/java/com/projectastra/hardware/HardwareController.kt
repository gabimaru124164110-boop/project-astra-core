package com.projectastra.hardware

interface HardwareDevice {
    val name: String
    fun connect(): Boolean
    fun disconnect(): Boolean
}

class MountController(
    override val name: String = "Project Astra mount controller",
) : HardwareDevice {
    override fun connect(): Boolean = true
    override fun disconnect(): Boolean = true
}

class HardwareController {
    private val devices = listOf(
        MountController("N.I.N.A. sync"),
        MountController("ASCOM / INDI bridge"),
        MountController("PHD2 guiding loop"),
    )

    fun connectedDevices(): List<String> = devices.map { it.name }
}
