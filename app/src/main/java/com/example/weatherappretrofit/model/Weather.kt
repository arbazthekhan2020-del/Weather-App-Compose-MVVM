package com.example.weatherappretrofit.model

data class Weather(
    val location: String,
    val weather: String,
    val temperature: Int,
    val windSpeed: Double
)
