package com.example.weatherappretrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocodingRetrofit {

    private val retrofit = Retrofit.Builder().baseUrl("https:/geocoding-api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(WeatherApi::class.java)
}