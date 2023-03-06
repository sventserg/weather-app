package com.example.weather.data.retrofit

import com.example.weather.data.api.CurrentWeatherApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.open-meteo.com"

/**
 * CurrentWeatherRetrofit - retrofit object for implementation of CurrentWeatherApi
 */

object CurrentWeatherRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val currentWeatherApi: CurrentWeatherApi = retrofit.create(
        CurrentWeatherApi::class.java
    )
}