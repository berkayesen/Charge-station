package com.berkayesen.afinal.retrofit2.data.model

data class UsageType(
    val ID: Int,
    val IsAccessKeyRequired: Boolean,
    val IsMembershipRequired: Boolean,
    val IsPayAtLocation: Boolean,
    val Title: String
)