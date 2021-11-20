package com.uguraltintas.weatherapp.adapter.weekly

import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.weatherapp.databinding.RecyclerRowWeeklyWeatherBinding
import com.uguraltintas.weatherapp.model.Daily


class WeeklyWeatherForecastViewHolder(private val binding: RecyclerRowWeeklyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Daily) {
        binding.daily = item
    }
}