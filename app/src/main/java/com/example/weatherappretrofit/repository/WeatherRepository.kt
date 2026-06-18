package com.example.weatherappretrofit.repository

import com.example.weatherappretrofit.api_model.LocationResult
import com.example.weatherappretrofit.api_model.WeatherResponse
import com.example.weatherappretrofit.mapper.getWeatherDescription
import com.example.weatherappretrofit.model.Weather
import com.example.weatherappretrofit.network.GeocodingRetrofit
import com.example.weatherappretrofit.network.RetrofitInstance
import kotlin.text.toInt

class WeatherRepository {
    suspend fun getCoordinates(
        city: String
    ): LocationResult?{

        return GeocodingRetrofit
            .api
            .searchLocation(city)
            .results
            .firstOrNull()
    }

    suspend fun getWeather(
        city: String
    ) : Weather {

        val location = getCoordinates(city)
            ?:throw Exception("City not found")

        val weatherResponse = RetrofitInstance.api.getWeather(
            location.latitude,
            location.longitude
        )

        return Weather(
            location = location.name,
            weather = getWeatherDescription(weatherResponse.current_weather.weathercode.toInt()),
            temperature = weatherResponse
                .current_weather
                .temperature
                .toInt(),
            windSpeed = weatherResponse.current_weather.windspeed
        )

    }
}