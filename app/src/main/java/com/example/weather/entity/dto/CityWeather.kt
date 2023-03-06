package com.example.weather.entity.dto

import com.example.weather.data.dto.CurrentWeatherDto
import com.example.weather.data.dto.WeatherDto

interface CityWeather {
    val offsetSeconds: Int
    val currentWeather: CurrentWeatherDto
    val weatherList: WeatherDto
}