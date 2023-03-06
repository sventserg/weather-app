package com.example.weather.data.repository

import com.example.weather.data.dto.CityListDto
import com.example.weather.data.retrofit.GeocodingRetrofit
import com.example.weather.entity.repository.GeocodingRepository

/**
 * GeocodingRepository - repository for getting city coordinates by city name
 *
 * getCityList - get list of matching cities and cities coordinates by string
 */

class GeocodingRepository : GeocodingRepository {
    override suspend fun getCityList(string: String) : CityListDto? {
        return GeocodingRetrofit.geocodingApi.getCity(string)
    }
}