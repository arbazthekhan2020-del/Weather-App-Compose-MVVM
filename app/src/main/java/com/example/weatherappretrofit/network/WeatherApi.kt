package com.example.weatherappretrofit.network

import com.example.weatherappretrofit.api_model.GeocodingResponse
import com.example.weatherappretrofit.api_model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") currentWeather: Boolean = true
    ): WeatherResponse

    @GET("v1/search")
    suspend fun searchLocation(
        @Query("name")
        city: String
    ): GeocodingResponse

}