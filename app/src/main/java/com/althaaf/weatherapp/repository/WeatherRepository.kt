package com.althaaf.weatherapp.repository

import com.althaaf.weatherapp.model.WeatherResponse
import com.althaaf.weatherapp.network.ApiService
import com.althaaf.weatherapp.utils.ApiResult

class WeatherRepository(
    private val apiService: ApiService
) {
    suspend fun getWeatherForecasting(city: String): ApiResult<WeatherResponse, Boolean, Exception> {
        val response = try {
            apiService.getWeatherForecast(city)
        } catch (e: Exception) {
            return ApiResult(error = e)
        }

        return ApiResult(data = response)
    }
}