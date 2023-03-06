package com.example.weather.domain

import com.example.weather.data.dto.CityWeatherDto
import com.example.weather.data.dto.CurrentWeatherDto
import com.example.weather.data.dto.WeatherDto
import com.example.weather.entity.domain.CurrentWeatherUseCase
import com.example.weather.entity.dto.CityWeather

const val FAKE_CURRENT_TEMPERATURE = "-2"
const val FAKE_CURRENT_WIND_SPEED = "5"
const val FAKE_CURRENT_WEATHER_CODE = 0
const val FAKE_TIME = 1666144800

class FakeCurrentWeatherUseCase : CurrentWeatherUseCase {
    override suspend fun execute(latitude: Double, longitude: Double): CityWeather? {
        val fakeTemperatureList = mutableListOf<String>()
        val fakeTimeList = mutableListOf<Int>()
        var counter = 0
        while (counter < 50) {
            fakeTemperatureList.add(counter.toString())
            fakeTimeList.add(counter)
            counter++
        }
        val fakeWeather = WeatherDto(
            fakeTemperatureList,
            fakeTimeList
        )

        val fakeCurrentTemperature = FAKE_CURRENT_TEMPERATURE
        val fakeWeatherCode = FAKE_CURRENT_WEATHER_CODE
        val fakeWindSpeed = FAKE_CURRENT_WIND_SPEED
        val fakeTime = FAKE_TIME

        val fakeCurrentWeather = CurrentWeatherDto(
            currentTemperature = fakeCurrentTemperature,
            weatherCode = fakeWeatherCode,
            windSpeed = fakeWindSpeed,
            time = fakeTime
        )

        return CityWeatherDto(
            offsetSeconds = FAKE_TIME,
            currentWeather = fakeCurrentWeather,
            weatherList = fakeWeather
        )
    }
}