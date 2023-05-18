package com.berkayesen.afinal.retrofit2.data.model

data class CheckinStatusType(
    val ID: Int,
    val IsAutomatedCheckin: Boolean,
    val IsPositive: Boolean,
    val Title: String
)