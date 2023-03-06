package com.example.weather.domain

import com.example.weather.entity.domain.DailyWeatherUseCase

class FakeDailyWeatherUseCase : DailyWeatherUseCase {
    override fun execute(temperatureList: List<String>): List<String> {
        return listOf("1", "2", "3", "4")
    }
}