package com.althaaf.weatherapp.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaaf.weatherapp.model.Unit
import com.althaaf.weatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewmodel @Inject constructor(private val weatherDbRepository: WeatherDbRepository): ViewModel() {
    private var _units = MutableStateFlow<List<Unit>>(emptyList())
    val units = _units.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDbRepository.getListUnits()
                .distinctUntilChanged()
                .collect {
                    if(units.value.isEmpty()) {
                        _units.value = listOf(Unit("Celcius °C"))
                    } else {
                        _units.value = it
                    }
                }
        }
    }

    fun insertNewUnit(unit: Unit) {
        viewModelScope.launch {
            weatherDbRepository.insertNewUnit(unit)
        }
    }

    fun deleteAllUnit() {
        viewModelScope.launch {
            weatherDbRepository.deleteUnit()
        }
    }
}