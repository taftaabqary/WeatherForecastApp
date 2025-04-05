package com.althaaf.weatherapp.repository

import com.althaaf.weatherapp.data.FavoriteDao
import com.althaaf.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(
    private val favoriteDao: FavoriteDao
) {
    fun getListFavoriteCity(): Flow<List<Favorite>> {
        return favoriteDao.getFavoriteCountry()
    }

    suspend fun getSpecificFavoriteCity(city: String): Favorite {
        return favoriteDao.getSpecificFavoriteCitY(city)
    }

    suspend fun insertFavoriteCity(favorite: Favorite) {
        return favoriteDao.insertCityFavorite(favorite)
    }

    suspend fun deleteFavoriteCity(favorite: Favorite) {
        return favoriteDao.deleteFavoriteCity(favorite)
    }
}