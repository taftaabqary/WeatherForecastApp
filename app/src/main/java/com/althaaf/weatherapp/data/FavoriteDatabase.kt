package com.althaaf.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.althaaf.weatherapp.model.Favorite

@Database(entities = [Favorite::class], exportSchema = false, version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}