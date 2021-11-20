package com.uguraltintas.weatherapp.adapter.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.weatherapp.databinding.RecyclerRowHourlyWeatherBinding
import com.uguraltintas.weatherapp.model.Hourly
import com.uguraltintas.weatherapp.model.Weather

class HourlyWeatherForecastAdapter(
    private val list: List<Hourly>
) : RecyclerView.Adapter<HourlyWeatherForecastViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyWeatherForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowHourlyWeatherBinding.inflate(inflater)
        return HourlyWeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherForecastViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}