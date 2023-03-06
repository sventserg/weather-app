package com.example.weather.data.repository

import android.content.Context
import com.example.weather.data.storage.SavedCitiesStorage
import com.example.weather.entity.dto.City
import com.example.weather.entity.repository.SavedCitiesRepository

/**
 * SavedCitiesRepository - repository for storing a list of saved cities
 */

class SavedCitiesRepository(context: Context) : SavedCitiesRepository {
    private val savedCitiesStorage = SavedCitiesStorage()

    init {
        savedCitiesStorage.getSavedCitiesPreferences(context)
    }

    override fun saveCity(city: City) {
        savedCitiesStorage.saveCityToPreferences(city)
    }

    override fun deleteCity(city: City) {
        savedCitiesStorage.deleteCityFromPreferences(city)
    }

    override fun getSavedCitiesList(): List<City>? {
        return savedCitiesStorage.getSavedCities()
    }


}