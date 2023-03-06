package com.example.weather.entity.repository

import com.example.weather.entity.dto.City

interface LastCityRepository {
    fun saveLastCity(city: City)

    fun getLastCity() : City?

    fun getLastCityName() : String
}