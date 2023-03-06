package com.example.weather.data.dto

import com.example.weather.entity.dto.CityList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

private const val RESULTS = "results"

/**
 * CityListDto - result of successful request of GeocodingApi, list of objects (CityDto)
 */

@JsonClass(generateAdapter = true)
class CityListDto(
    @Json(name = RESULTS) override val cityList: List<CityDto>?
) : CityList