package com.example.weatherappretrofit.uiState

import com.example.weatherappretrofit.model.Weather

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null
)