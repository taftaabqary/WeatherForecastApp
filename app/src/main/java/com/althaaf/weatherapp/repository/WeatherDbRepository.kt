package com.althaaf.weatherapp.repository

import com.althaaf.weatherapp.data.FavoriteDao
import com.althaaf.weatherapp.model.Favorite
import com.althaaf.weatherapp.model.Unit
import kotlinx.coroutines.flow.Flow

class WeatherDbRepository(
    private val favoriteDao: FavoriteDao
) {
    fun getListFavoriteCity(): Flow<List<Favorite>> {
        return favoriteDao.getFavoriteCountry()
    }

    suspend fun insertFavoriteCity(favorite: Favorite) {
        return favoriteDao.insertCityFavorite(favorite)
    }

    suspend fun deleteFavoriteCity(favorite: Favorite) {
        return favoriteDao.deleteFavoriteCity(favorite)
    }

    fun getListUnits(): Flow<List<Unit>> {
        return favoriteDao.getListUnit()
    }

    suspend fun insertNewUnit(unit: Unit) {
        return favoriteDao.insertSaveUnit(unit)
    }

    suspend fun deleteUnit() {
        return favoriteDao.deleteUnits()
    }
}