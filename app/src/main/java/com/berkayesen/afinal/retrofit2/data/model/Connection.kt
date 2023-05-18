package com.berkayesen.afinal.retrofit2.data.model

data class Connection(
    val Amps: Int,
    val Comments: String,
    val ConnectionType: ConnectionType,
    val ConnectionTypeID: Int,
    val CurrentType: CurrentType,
    val CurrentTypeID: Int,
    val ID: Int,
    val Level: Level,
    val LevelID: Int,
    val PowerKW: Double,
    val Quantity: Int,
    val Reference: String,
    val StatusType: StatusTypeX,
    val StatusTypeID: Int,
    val Voltage: Int
)