package com.pm.weatherapp.api

import com.pm.weatherapp.model.Forecast
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * A retrofit service to fetch forecasts
 */
interface ApiService {
    @GET("forecast")
    suspend fun getForecasts(): ForecastResponse
}

class ForecastResponse {
    @SerializedName("forecasts")
    lateinit var forecasts: List<Forecast>
}

/**
 * Main entry point for network access
 */
object ForecastNetwork {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://weather.aw.ee/api/estonia/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}


