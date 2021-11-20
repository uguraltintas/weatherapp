package com.uguraltintas.weatherapp.network

import com.uguraltintas.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("onecall?exclude=minutely&units=metric")
    fun getWeatherData(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("appid") appId : String,
        @Query("lang") lang : String
    ): Call<Weather>
}