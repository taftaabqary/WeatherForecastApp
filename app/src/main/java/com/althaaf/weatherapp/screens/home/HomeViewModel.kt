package com.althaaf.weatherapp.screens.home

import androidx.lifecycle.ViewModel
import com.althaaf.weatherapp.model.WeatherResponse
import com.althaaf.weatherapp.repository.WeatherRepository
import com.althaaf.weatherapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    suspend fun getWeatherForecasting(): ApiResult<WeatherResponse, Boolean, Exception> {
        return weatherRepository.getWeatherForecasting("Bandung")
    }
}