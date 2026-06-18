package com.example.weatherappretrofit.mapper

fun getWeatherDescription(
    code: Int
): String{
    return when(code){
        0 -> "Clear Sky"
        1 -> "Mainly Clear"
        2 -> "Partly Cloudy"
        3 -> "Overcast"
        45, 48 -> "Fog"
        51, 53, 55 -> "Drizzle"
        61, 63, 65 -> "Rain"
        71, 73, 75 -> "Snow"
        80, 81, 82 -> "Rain Showers"
        95 -> "Thunderstorm"
        else -> "Unknown"
    }
}