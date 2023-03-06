package com.example.weather.data.repository

import android.content.Context
import com.example.weather.data.storage.LastCityStorage
import com.example.weather.entity.dto.City
import com.example.weather.entity.repository.LastCityRepository

/**
 * LastCityRepository - repository for storing information about last selected city
 */

class LastCityRepository(context:Context) : LastCityRepository {
    private val lastCityStorage = LastCityStorage()

    init {
        lastCityStorage.getLastCityPreferences(context)
    }

    override fun saveLastCity(city: City) {
        lastCityStorage.putLastCityToPreferences(city)
    }

    override fun getLastCity() : City? {
       return lastCityStorage.getLastCityFromPreferences()
    }

    override fun getLastCityName() : String {
        return lastCityStorage.getLastCityName()
    }

}