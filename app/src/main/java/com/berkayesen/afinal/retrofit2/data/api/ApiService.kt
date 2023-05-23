package com.berkayesen.afinal.retrofit2.data.api

import com.berkayesen.afinal.retrofit2.data.model.ChargeJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v3/poi/")
    fun getChargeData(
        @Query("key") key: String,
        @Query("output") output: String,
        @Query("countrycode") countryCode: String,
        @Query("maxresults") maxResults: String,

    ): Call<ChargeJson>
}