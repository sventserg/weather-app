package com.example.weather.entity.domain

import com.example.weather.entity.dto.CityWeather

interface CurrentWeatherUseCase {
    suspend fun execute(latitude: Double, longitude: Double): CityWeather?
}