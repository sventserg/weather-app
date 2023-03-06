package com.example.weather.entity.domain

import com.example.weather.entity.dto.CityList

interface CityListUseCase {
    suspend fun execute(string: String): CityList?
}