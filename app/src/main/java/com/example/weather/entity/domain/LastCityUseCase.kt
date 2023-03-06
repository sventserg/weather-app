package com.example.weather.entity.domain

import com.example.weather.entity.dto.City

interface LastCityUseCase {
    fun saveLastCity(city: City)

    fun getLastCity() : City?

    fun getLastCityName() : String
}