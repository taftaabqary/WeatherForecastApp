package com.althaaf.weatherapp.repository

import com.althaaf.weatherapp.model.WeatherResponse
import com.althaaf.weatherapp.network.ApiService
import com.althaaf.weatherapp.utils.ApiResult

class WeatherRepository(
    private val apiService: ApiService
) {
    suspend fun getWeatherForecasting(city: String, units: String): ApiResult<WeatherResponse, Boolean, Exception> {
        val response = try {
            if(units.isEmpty()) {
                apiService.getWeatherForecast(city)
            } else {
                apiService.getWeatherForecast(city, units = units)
            }
        } catch (e: Exception) {
            return ApiResult(error = e)
        }

        return ApiResult(data = response)
    }
}