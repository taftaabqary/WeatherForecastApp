package com.althaaf.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.althaaf.weatherapp.model.Favorite
import com.althaaf.weatherapp.model.Unit

@Database(entities = [Favorite::class, Unit::class], exportSchema = false, version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}