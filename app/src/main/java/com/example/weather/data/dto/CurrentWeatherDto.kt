package com.example.weather.data.dto

import com.example.weather.entity.dto.CurrentWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val TEMPERATURE = "temperature"
private const val WEATHER_CODE = "weathercode"
private const val WIND_SPEED = "windspeed"
private const val TIME = "time"

/**
 * CurrentWeatherDto - result of successful request of CurrentWeatherApi, describing current weather conditions
 *
 * temperature - current temperature
 * weathercode - weather interpretation code
 * windspeed - current wind speed
 * time - current local time
 */

@JsonClass(generateAdapter = true)
class CurrentWeatherDto(
    @Json(name = TEMPERATURE) override val currentTemperature: String,
    @Json(name = WEATHER_CODE) override val weatherCode: Int,
    @Json(name = WIND_SPEED) override val windSpeed: String,
    @Json(name = TIME) override val time: Int
) : CurrentWeather