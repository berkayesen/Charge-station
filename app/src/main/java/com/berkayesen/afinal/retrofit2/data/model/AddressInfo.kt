package com.berkayesen.afinal.retrofit2.data.model

data class AddressInfo(
    val AccessComments: String,
    val AddressLine1: String,
    val AddressLine2: String,
    val ContactEmail: Any,
    val ContactTelephone1: String,
    val ContactTelephone2: Any,
    val Country: Country,
    val CountryID: Int,
    val Distance: Any,
    val DistanceUnit: Int,
    val ID: Int,
    val Latitude: Double,
    val Longitude: Double,
    val Postcode: String,
    val RelatedURL: String,
    val StateOrProvince: String,
    val Title: String,
    val Town: String
)