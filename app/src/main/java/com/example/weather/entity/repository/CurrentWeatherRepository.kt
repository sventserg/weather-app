package com.example.weather.entity.repository

import com.example.weather.entity.dto.CityWeather

interface CurrentWeatherRepository {
    suspend fun getWeatherList(
        latitude: Double,
        longitude: Double
    ): CityWeather?
}