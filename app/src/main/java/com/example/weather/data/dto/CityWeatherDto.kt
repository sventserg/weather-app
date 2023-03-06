package com.example.weather.data.dto

import com.example.weather.entity.dto.CityWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val UTC_OFFSET_SECONDS = "utc_offset_seconds"
private const val CURRENT_WEATHER = "current_weather"
private const val HOURLY = "hourly"

/**
 * CityWeatherDto - result of successful request of GeocodingApi, list of objects (CityDto)
 *
 * utc_offset_seconds - applied timezone offset from the "timezone" parameter, this parameter isn't used in this project
 * current_weather - current weather conditions
 * hourly - hourly temperature list
 */

@JsonClass(generateAdapter = true)
class CityWeatherDto(
    @Json(name = UTC_OFFSET_SECONDS) override val offsetSeconds: Int,
    @Json(name = CURRENT_WEATHER) override val currentWeather: CurrentWeatherDto,
    @Json(name = HOURLY) override val weatherList: WeatherDto
) : CityWeather