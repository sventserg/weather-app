package com.example.weather.entity.repository

import com.example.weather.entity.dto.City

interface SavedCitiesRepository {

    fun saveCity(city: City)

    fun deleteCity(city: City)

    fun getSavedCitiesList() : List<City>?
}