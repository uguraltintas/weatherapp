package com.uguraltintas.weatherapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.uguraltintas.weatherapp.R
import com.uguraltintas.weatherapp.model.Season
import java.util.*

@BindingAdapter("setColorTheme")
fun View.setColorTheme(season: Season) {
    when (season) {
        Season.AUTUMN -> {
            if (this is RecyclerView) {
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rounded_corners_autumn, null)
            } else {
                setBackgroundColor(ResourcesCompat.getColor(resources, R.color.autumn, null))
            }
        }
        Season.WINTER -> {
            if (this is RecyclerView) {
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rounded_corners_winter, null)
            } else {
                setBackgroundColor(ResourcesCompat.getColor(resources, R.color.winter, null))
            }
        }
        Season.SPRING -> {
            if (this is RecyclerView) {
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rounded_corners_spring, null)
            } else {
                setBackgroundColor(ResourcesCompat.getColor(resources, R.color.spring, null))
            }
        }
        Season.SUMMER -> {
            if (this is RecyclerView) {
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rounded_corners_summer, null)
            } else {
                setBackgroundColor(ResourcesCompat.getColor(resources, R.color.summer, null))
            }
        }
    }
}

@BindingAdapter("setBackgroundAnimation")
fun LottieAnimationView.setBackgroundAnimation(season: Season) {
    when (season) {
        Season.AUTUMN -> {
            setAnimation(R.raw.fall)
        }
        Season.WINTER -> {
            setAnimation(R.raw.winter)
        }
        Season.SPRING -> {
            setAnimation(R.raw.spring)
        }
        Season.SUMMER -> {
            setAnimation(R.raw.summer)
            alpha = 1F
        }
    }
}

@BindingAdapter("unixSeconds", "timezone")
fun TextView.convertTime(unixSeconds: Long, timezone: String) {
    val date = Date(unixSeconds * 1000L)
    val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
    cal.time = date
    cal.timeZone = TimeZone.getTimeZone(timezone)
    text = if (cal.get(Calendar.HOUR_OF_DAY) / 10 <= 0) {
        val hour = "0" + cal.get(Calendar.HOUR_OF_DAY).toString()
        hour
    } else {
        cal.get(Calendar.HOUR_OF_DAY).toString()
    }

}

@BindingAdapter("convertTimeToDay")
fun TextView.convertTimeToDay(unixSeconds: Long) {
    val date = Date(unixSeconds * 1000L)
    val cal = Calendar.getInstance()
    cal.time = date
    when (cal.get(Calendar.DAY_OF_WEEK)) {
        1 -> text = resources.getString(R.string.sunday)
        2 -> text = resources.getString(R.string.monday)
        3 -> text = resources.getString(R.string.tuesday)
        4 -> text = resources.getString(R.string.wednesday)
        5 -> text = resources.getString(R.string.thursday)
        6 -> text = resources.getString(R.string.friday)
        7 -> text = resources.getString(R.string.saturday)
    }

}

@BindingAdapter("setTemperatureToInt")
fun TextView.setTemperatureToInt(temp: Double) {
    val temperature = temp.toInt()
    // Celsius symbol u2103
    // Fahrenheit symbol u2109
    text = "$temperature \u2103"
}

@BindingAdapter("setImage")
fun ImageView.setImage(icon: String?) {
    val iconUrl = "https://openweathermap.org/img/w/${icon}.png"
    Glide
        .with(this)
        .load(iconUrl)
        .into(this);
}

@BindingAdapter("setVisibility")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}