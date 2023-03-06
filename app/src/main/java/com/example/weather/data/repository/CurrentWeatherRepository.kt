package com.example.weather.data.repository

import com.example.weather.data.dto.CityWeatherDto
import com.example.weather.data.retrofit.CurrentWeatherRetrofit
import com.example.weather.entity.repository.CurrentWeatherRepository

/**
 * CurrentWeatherRepository - repository for getting current weather information
 */

class CurrentWeatherRepository : CurrentWeatherRepository {

    override suspend fun getWeatherList(
        latitude: Double,
        longitude: Double
    ): CityWeatherDto? {
        return CurrentWeatherRetrofit.currentWeatherApi.getWeather(latitude, longitude)
    }
}