package com.althaaf.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "unit_tbl")
data class Unit(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String
)
