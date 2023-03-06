package com.example.weather.entity.dto

interface CurrentWeather {
    val currentTemperature: String
    val weatherCode: Int
    val windSpeed: String
    val time: Int
}