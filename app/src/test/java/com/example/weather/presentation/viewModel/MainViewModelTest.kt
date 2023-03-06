package com.example.weather.presentation.viewModel

import android.util.Log
import com.example.weather.domain.*
import com.example.weather.entity.domain.CityListUseCase
import com.example.weather.entity.domain.SavedCitiesUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val cityListUseCase = mockk<CityListUseCase>()
    private val savedCitiesUseCase = mockk<SavedCitiesUseCase>()
    private val currentWeatherUseCase = FakeCurrentWeatherUseCase()
    private val dailyWeatherUseCase = FakeDailyWeatherUseCase()
    private val lastCityUseCase = FakeLastCityUseCase()

    private val textData = "Text data"

    private var viewModel = MainViewModel(
        cityListUseCase,
        currentWeatherUseCase,
        dailyWeatherUseCase,
        lastCityUseCase,
        savedCitiesUseCase
    )

    @Before
    fun getCurrentCityWeather() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        runBlockingTest {
            viewModel.getCurrentCityWeather()
        }
    }

    @Test
    fun locationPermission() {
        val startPermission = viewModel.getIsLocationPermissionsGranted()
        viewModel.locationPermissionsGranted()
        val isPermissionGranted = viewModel.getIsLocationPermissionsGranted()

        Assert.assertEquals(startPermission, false)
        Assert.assertEquals(isPermissionGranted, true)
    }

    @Test
    fun currentWeather() {

        val currentTemperature = viewModel.getCurrentTemperature()
        val currentWindSpeed = viewModel.getCurrentWindSpeed()
        val currentWeatherCode = viewModel.getCurrentWeatherCode()

        Assert.assertEquals(currentTemperature, FAKE_CURRENT_TEMPERATURE)
        Assert.assertEquals(currentWindSpeed, FAKE_CURRENT_WIND_SPEED)
        Assert.assertEquals(currentWeatherCode, FAKE_CURRENT_WEATHER_CODE)
    }

}