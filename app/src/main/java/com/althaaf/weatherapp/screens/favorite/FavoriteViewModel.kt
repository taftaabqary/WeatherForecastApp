package com.althaaf.weatherapp.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaaf.weatherapp.model.Favorite
import com.althaaf.weatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: WeatherDbRepository): ViewModel() {
    private var _favorite = MutableStateFlow<List<Favorite>>(emptyList())
    val favorite = _favorite.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.getListFavoriteCity()
                .distinctUntilChanged()
                .collect {
                    _favorite.value = it
                }
        }
    }

    fun insertFavCity(favorite: Favorite) {
        viewModelScope.launch {
            favoriteRepository.insertFavoriteCity(favorite)
        }
    }

    fun deleteFavCity(favorite: Favorite) {
        viewModelScope.launch {
            favoriteRepository.deleteFavoriteCity(favorite)
        }
    }
}