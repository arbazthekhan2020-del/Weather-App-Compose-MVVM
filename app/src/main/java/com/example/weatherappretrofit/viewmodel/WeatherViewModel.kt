package com.example.weatherappretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappretrofit.model.Weather
import com.example.weatherappretrofit.repository.WeatherRepository
import com.example.weatherappretrofit.uiState.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel()  {

    private val repository = WeatherRepository()

    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState = _uiState.asStateFlow()

    fun getWeather(city: String){
        if (city.isBlank()){
            _uiState.value = WeatherUiState(error = "Enter a city")
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = WeatherUiState(isLoading = true)


                _uiState.value = WeatherUiState(weather = repository.getWeather(city))

            }
            catch (e: Exception){
                _uiState.value = WeatherUiState(error = e.message ?: "Unknown error")
            }
        }
    }
}