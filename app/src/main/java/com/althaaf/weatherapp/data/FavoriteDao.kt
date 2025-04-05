package com.althaaf.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.althaaf.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM fav_tbl")
    fun getFavoriteCountry(): Flow<List<Favorite>>

    @Query("SELECT * FROM fav_tbl WHERE city = :city")
    suspend fun getSpecificFavoriteCitY(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityFavorite(favorite: Favorite)

    @Delete()
    suspend fun deleteFavoriteCity(favorite: Favorite)
}