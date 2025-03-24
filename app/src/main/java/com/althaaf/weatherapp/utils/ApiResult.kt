package com.althaaf.weatherapp.utils

data class ApiResult<T, Boolean, E: Exception>(
    val data: T? = null,
    val loading: Boolean? = null,
    val error: E? = null
)
