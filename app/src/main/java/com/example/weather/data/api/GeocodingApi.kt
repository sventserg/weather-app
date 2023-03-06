package com.example.weather.data.api

import com.example.weather.data.dto.CityListDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val PATH = "/v1/search"
private const val QUERY_PARAMETER_NAME = "name"

/**
 * API based on Geocoding API (https://open-meteo.com/en/docs/geocoding-api)
 */

interface GeocodingApi {
    @GET(PATH)
    suspend fun getCity(
        @Query(QUERY_PARAMETER_NAME) name: String
    ) : CityListDto?
}