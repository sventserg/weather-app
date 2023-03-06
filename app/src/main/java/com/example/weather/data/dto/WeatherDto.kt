package com.example.weather.data.dto

import com.example.weather.entity.dto.Weather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val TEMPERATURE_2M = "temperature_2m"
private const val TIME = "time"

/**
 * WeatherDto - class describing hourly air temperature
 *
 * temperature_2m - list of air temperatures at 2 meters above ground
 * time - list of hours, each element of this list is fit to corresponding element of temperatures list
 */

@JsonClass(generateAdapter = true)
class WeatherDto(
    @Json(name = TEMPERATURE_2M) override val temperatureList: List<String>,
    @Json(name = TIME) override val timeList: List<Int>
) : Weather