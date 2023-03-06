package com.example.weather.domain

import com.example.weather.data.dto.CityDto
import com.example.weather.entity.domain.LastCityUseCase
import com.example.weather.entity.dto.City

const val FAKE_CITY_NAME = "Fake city"

class FakeLastCityUseCase : LastCityUseCase {

    val fakeCity = CityDto(
        name = FAKE_CITY_NAME,
        latitude = 1.0,
        longitude = 1.0,
        country = null
    )


    override fun saveLastCity(city: City) {
        TODO("Not yet implemented")
    }

    override fun getLastCity(): City? {
        return fakeCity
    }

    override fun getLastCityName(): String {
        return fakeCity.name
    }
}