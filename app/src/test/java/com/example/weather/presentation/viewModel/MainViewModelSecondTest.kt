package com.example.weather.presentation.viewModel

import android.util.Log
import com.example.weather.data.dto.CityDto
import com.example.weather.domain.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelSecondTest {

    private val cityListUseCase = mockk<CityListUseCase>()
    private val savedCitiesUseCase = mockk<SavedCitiesUseCase>()
    private val currentWeatherUseCase = mockk<CurrentWeatherUseCase>()
    private val dailyWeatherUseCase = mockk<DailyWeatherUseCase>()
    private val lastCityUseCase = mockk<LastCityUseCase>()

    private val viewModel = MainViewModel(
        cityListUseCase,
        currentWeatherUseCase,
        dailyWeatherUseCase,
        lastCityUseCase,
        savedCitiesUseCase
    )

    private val randomCity = CityDto(
        name = "City name",
        latitude = 5.0,
        longitude = 5.0,
        country = "Country name"
    )

    private var lastCity = CityDto(
        name = "No city",
        latitude = 0.0,
        longitude = 0.0,
        country = "No country"
    )

    private val firstCity = CityDto(
        name = "First city",
        latitude = 1.0,
        longitude = 1.0,
        country = "First country"
    )

    private val secondCity = CityDto(
        name = "Second city",
        latitude = 2.0,
        longitude = 2.0,
        country = "Second country"
    )

    private val cityList = mutableListOf(firstCity, secondCity)

    private fun addCity(city: CityDto) {
        cityList.add(city)
    }

    private fun saveLastCity(city: CityDto) {
        lastCity = city
    }

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { lastCityUseCase.saveLastCity(randomCity) } returns saveLastCity(randomCity)
        every { lastCityUseCase.getLastCityName() } returns lastCity.name
        every { savedCitiesUseCase.saveCity(randomCity) } returns addCity(randomCity)
        every { savedCitiesUseCase.getSavedCitiesList() } returns cityList
    }

    @Test
    fun saveLastCityTest() {
        viewModel.saveLastCity(randomCity)
        Assert.assertEquals(lastCity, randomCity)
        verify { lastCityUseCase.saveLastCity(randomCity) }
    }

    @Test
    fun getLastCity() {
        val name = viewModel.getLastCityName()
        Assert.assertEquals(name, lastCity.name)
        verify { lastCityUseCase.getLastCityName() }
    }

    @Test
    fun saveCityTest() {
        viewModel.saveCity(randomCity)
        Assert.assertEquals(randomCity, cityList.last())
        verify { savedCitiesUseCase.saveCity(randomCity) }
    }

    @Test
    fun getCityList() {
        val list = viewModel.getSavedCities()
        Assert.assertEquals(list, cityList)
        verify { savedCitiesUseCase.getSavedCitiesList() }
    }


}