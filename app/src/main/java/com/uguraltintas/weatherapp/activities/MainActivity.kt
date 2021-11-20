package com.uguraltintas.weatherapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.uguraltintas.weatherapp.BuildConfig
import com.uguraltintas.weatherapp.R
import com.uguraltintas.weatherapp.adapter.hourly.HourlyWeatherForecastAdapter
import com.uguraltintas.weatherapp.adapter.weekly.WeeklyWeatherForecastAdapter
import com.uguraltintas.weatherapp.databinding.ActivityMainBinding
import com.uguraltintas.weatherapp.model.Weather
import com.uguraltintas.weatherapp.network.RetrofitClient
import com.uguraltintas.weatherapp.network.WeatherService
import retrofit2.Call
import retrofit2.Response
import com.uguraltintas.weatherapp.model.Season
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // setContentView(R.layout.activity_main)
        val bundle = intent.extras
        val latitude = bundle!!.getDouble("lat")
        val longitude = bundle.getDouble("lon")
        val cityName = bundle.getString("cityName")
        val apiKey = BuildConfig.API_KEY // Open Weather Map Api Key
        val language = Locale.getDefault().language
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        binding.season = getSeason(month) //Season.WINTER
        binding.ltLoadingVisibility = true
        binding.clVisibility = false

        RetrofitClient.getClient().create(WeatherService::class.java)
            .getWeatherData(latitude, longitude, apiKey, language)
            .enqueue(object : retrofit2.Callback<Weather> {
                override fun onResponse(
                    call: Call<Weather>,
                    response: Response<Weather>
                ) {
                    val weatherData: Weather? = response.body()
                    println(weatherData)
                    weatherData?.let {
                        weatherData.city = cityName
                        weatherData.hourly.forEach { hourly ->
                            hourly.timezone = weatherData.timezone
                        }
                        binding.weather = weatherData
                        binding.rvWeaklyWeather.adapter =
                            WeeklyWeatherForecastAdapter(weatherData.daily)
                        binding.rvHourlyWeather.adapter =
                            HourlyWeatherForecastAdapter(weatherData.hourly)
                        binding.ltLoadingVisibility = false
                        binding.clVisibility = true
                    }
                }
                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    println(t)
                }
            })

        binding.rvWeaklyWeather.layoutManager = LinearLayoutManager(this)
        binding.rvHourlyWeather.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getSeason(month: Int): Season {
        val months = arrayListOf(
            Season.WINTER,
            Season.WINTER,
            Season.SPRING,
            Season.SPRING,
            Season.SPRING,
            Season.SUMMER,
            Season.SUMMER,
            Season.SUMMER,
            Season.AUTUMN,
            Season.AUTUMN,
            Season.AUTUMN,
            Season.WINTER,
        )
        return months[month]
    }

}