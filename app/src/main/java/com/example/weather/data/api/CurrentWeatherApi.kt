package com.example.weather.data.api

import com.example.weather.data.dto.CityWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY_PARAMETER_LATITUDE = "latitude"
private const val QUERY_PARAMETER_LONGITUDE = "longitude"
private const val QUERY_PARAMETER_HOURLY = "hourly"
private const val QUERY_PARAMETER_CURRENT_WEATHER = "current_weather"
private const val QUERY_PARAMETER_TIME_FORMAT = "timeformat"
private const val QUERY_PARAMETER_TIME_ZONE = "timezone"
private const val QUERY_PARAMETER_WIND_SPEED = "windspeed_unit"
private const val PATH = "/v1/forecast"
private const val HOURLY_PARAMETER_TEMPERATURE_2M = "temperature_2m"
private const val TIME_FORMAT_PARAMETER_UNIXTIME = "unixtime"
private const val TIME_ZONE_PARAMETER_AUTO = "auto"
private const val WIND_SPEED_PARAMETER_MS = "ms"

/**
 * API based on Weather Forecast API (https://open-meteo.com/en/docs)
 *
 * latitude and longitude - coordinates of the location
 * "temperature_2m" - air temperature at 2 meters above ground
 * currentWeather - include current weather conditions
 * "unixtime" - all time values  are returned in UNIX epoch time in seconds
 * timeZone "auto" - all timestamps are returned as local-time
 * windSpeedFormat "ms" - speed unit (meter per second)
 */

interface CurrentWeatherApi {
    @GET(PATH)
    suspend fun getWeather(
        @Query(QUERY_PARAMETER_LATITUDE) latitude: Double,
        @Query(QUERY_PARAMETER_LONGITUDE) longitude: Double,
        @Query(QUERY_PARAMETER_HOURLY) hourly: String = HOURLY_PARAMETER_TEMPERATURE_2M,
        @Query(QUERY_PARAMETER_CURRENT_WEATHER) currentWeather: Boolean = true,
        @Query(QUERY_PARAMETER_TIME_FORMAT) timeFormat: String = TIME_FORMAT_PARAMETER_UNIXTIME,
        @Query(QUERY_PARAMETER_TIME_ZONE) timeZone: String = TIME_ZONE_PARAMETER_AUTO,
        @Query(QUERY_PARAMETER_WIND_SPEED) windSpeedFormat: String = WIND_SPEED_PARAMETER_MS
    ) : CityWeatherDto?
}