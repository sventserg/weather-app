package com.example.weather.entity.domain

interface DailyWeatherUseCase {
    fun execute(temperatureList: List<String>): List<String>
}