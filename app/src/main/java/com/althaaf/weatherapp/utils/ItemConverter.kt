package com.althaaf.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Locale

object ItemConverter {
    fun convertDecimalTemp(temp: Double): String {
        return "%.0f".format(temp)
    }

    fun convertTimeZoneToDate(time: Int): String {
        val sdf = SimpleDateFormat("EEE, MMM d yyyy", Locale.getDefault())
        val date = java.util.Date(time.toLong() * 1000)

        return sdf.format(date)
    }

    fun convertTimeToString(timeStamp: Int): String {
        val sdf = SimpleDateFormat("hh:MM aa", Locale.getDefault())
        val time = java.util.Date(timeStamp.toLong() * 1000)

        return sdf.format(time)
    }
}