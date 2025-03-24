package com.althaaf.weatherapp.network

import com.althaaf.weatherapp.BuildConfig
import com.althaaf.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherForecast(
        @Query("q") q: String,
        @Query("cnt") cnt: Int = 7,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): WeatherResponse
}