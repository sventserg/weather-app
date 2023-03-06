package com.example.weather.entity.repository

import com.example.weather.entity.dto.CityList

interface GeocodingRepository {
    suspend fun getCityList(string: String) : CityList?
}