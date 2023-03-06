package com.example.weather.domain

import com.example.weather.entity.domain.LastCityUseCase
import com.example.weather.entity.dto.City
import com.example.weather.entity.repository.LastCityRepository

/**
 * saveLastCity - save information about last selected city
 * getLastCity - get information about last selected city
 * getLastCityName - get last selected city name
 */

class LastCityUseCase(
    val repository: LastCityRepository
) : LastCityUseCase {
    override fun saveLastCity(city: City) {
        repository.saveLastCity(city)
    }

    override fun getLastCity() : City? {
        return repository.getLastCity()
    }

    override fun getLastCityName() : String {
        return repository.getLastCityName()
    }
}