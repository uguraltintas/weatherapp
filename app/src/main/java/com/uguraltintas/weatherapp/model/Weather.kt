package com.uguraltintas.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Weather(
    val current: Current,
    var city: String? = null,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)