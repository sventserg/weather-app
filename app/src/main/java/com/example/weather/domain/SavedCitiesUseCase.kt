package com.example.weather.domain

import com.example.weather.entity.domain.SavedCitiesUseCase
import com.example.weather.entity.dto.City
import com.example.weather.entity.repository.SavedCitiesRepository

/**
 * saveCity - save city to list of saved cities
 * deleteCity - delete city from list of saved cities
 * getSavedCitiesList - get list of saved cities
 */

class SavedCitiesUseCase(
    val repository: SavedCitiesRepository
) : SavedCitiesUseCase {
    override fun saveCity(city: City) {
        repository.saveCity(city)
    }

    override fun deleteCity(city: City) {
        repository.deleteCity(city)
    }

    override fun getSavedCitiesList() : List<City>? {
        return repository.getSavedCitiesList()
    }

}