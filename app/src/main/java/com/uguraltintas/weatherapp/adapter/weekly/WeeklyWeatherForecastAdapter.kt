package com.uguraltintas.weatherapp.adapter.weekly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.weatherapp.R
import com.uguraltintas.weatherapp.databinding.RecyclerRowWeeklyWeatherBinding
import com.uguraltintas.weatherapp.model.Daily

class WeeklyWeatherForecastAdapter(
    private val list: List<Daily>
) : RecyclerView.Adapter<WeeklyWeatherForecastViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeeklyWeatherForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowWeeklyWeatherBinding.inflate(inflater,parent,false)
        return WeeklyWeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeeklyWeatherForecastViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}