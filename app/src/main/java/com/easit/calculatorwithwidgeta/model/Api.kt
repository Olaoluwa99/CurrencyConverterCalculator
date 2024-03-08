package com.easit.calculatorwithwidgeta.model

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("latest")//@GET("latest")
    suspend fun getCurrencyData(
        @Query("apikey") apiKey: String
    ): Currency

    companion object {
        const val BASE_URL = "https://api.freecurrencyapi.com/v1/"
    }
}