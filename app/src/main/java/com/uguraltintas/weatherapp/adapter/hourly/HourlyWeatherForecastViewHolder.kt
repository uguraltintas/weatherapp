package com.uguraltintas.weatherapp.adapter.hourly

import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.weatherapp.databinding.RecyclerRowHourlyWeatherBinding
import com.uguraltintas.weatherapp.model.Daily
import com.uguraltintas.weatherapp.model.Hourly
import com.uguraltintas.weatherapp.model.Weather

class HourlyWeatherForecastViewHolder(
    private val binding : RecyclerRowHourlyWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Hourly) {
        binding.hourly = item
    }
}