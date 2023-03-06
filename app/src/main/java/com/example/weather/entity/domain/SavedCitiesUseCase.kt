package com.example.weather.entity.domain

import com.example.weather.entity.dto.City

interface SavedCitiesUseCase {
    fun saveCity(city: City)

    fun deleteCity(city: City)

    fun getSavedCitiesList() : List<City>?
}