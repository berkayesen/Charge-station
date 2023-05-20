package com.berkayesen.afinal.retrofit2.data.api

import com.berkayesen.afinal.retrofit2.data.model.ChargeJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/poi?key=c1916f96-601b-4ca9-bfdb-8b5f95eb84e5")
    fun getChargeData(): Call<ChargeJson>
}