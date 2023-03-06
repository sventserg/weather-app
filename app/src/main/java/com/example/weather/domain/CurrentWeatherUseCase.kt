package com.example.weather.domain

import com.example.weather.entity.domain.CurrentWeatherUseCase
import com.example.weather.entity.dto.CityWeather
import com.example.weather.entity.repository.CurrentWeatherRepository

/**
 * CurrentWeatherUseCase - get current weather conditions and hourly weather information by coordinates
 */

class CurrentWeatherUseCase(
    val repository: CurrentWeatherRepository
) : CurrentWeatherUseCase {
    override suspend fun execute(latitude: Double, longitude: Double): CityWeather? {
        return repository.getWeatherList(latitude = latitude, longitude = longitude)
    }
}