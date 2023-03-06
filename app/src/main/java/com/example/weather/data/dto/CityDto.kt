package com.example.weather.data.dto

import com.example.weather.entity.dto.City
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val NAME = "name"
private const val LATITUDE = "latitude"
private const val LONGITUDE = "longitude"
private const val COUNTRY = "country"

/**
 * CityDto - class describing result of successful request of GeocodingApi
 *
 * name - city name
 * latitude and longitude - city coordinates
 * country - country name in which city is located, nullable
 */

@JsonClass(generateAdapter = true)
class CityDto(
    @Json(name = NAME) override val name: String,
    @Json(name = LATITUDE) override val latitude: Double,
    @Json(name = LONGITUDE) override val longitude: Double,
    @Json(name = COUNTRY) override val country: String?
) : City