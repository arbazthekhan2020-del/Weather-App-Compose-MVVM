package com.example.weatherappretrofit.weatherIconsmapping

import android.media.Image
import com.example.weatherappretrofit.R

fun weatherIconsMap(weatherDescription: String): Int{
    return when(weatherDescription){
        "Clear Sky" -> R.drawable.sun
        "Mainly Clear" -> R.drawable.sun
        "Partly Cloudy" -> R.drawable.partlycloudy
        "Overcast" -> R.drawable.overcast
        "Fog" -> R.drawable.fog
        "Drizzle" -> R.drawable.drizzle
        "Rain" -> R.drawable.rainy
        "Snow" -> R.drawable.snow
        "Rain Showers" -> R.drawable.showers
        "Thunderstorm" -> R.drawable.thunderstorm
        else -> R.drawable.unknown
    }
}