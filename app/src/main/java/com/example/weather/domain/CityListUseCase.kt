package com.example.weather.domain

import com.example.weather.entity.domain.CityListUseCase
import com.example.weather.entity.dto.CityList
import com.example.weather.entity.repository.GeocodingRepository

/**
 * CityListUseCase - get list of matching cities and cities coordinates by string
 */

class CityListUseCase (
    val repository: GeocodingRepository
) : CityListUseCase {
    override suspend fun execute(string: String): CityList? {
        return repository.getCityList(string)
    }
}