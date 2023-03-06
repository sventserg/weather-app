package com.example.weather.presentation.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weather.R
import com.example.weather.data.dto.CityDto
import com.example.weather.entity.domain.*
import com.example.weather.entity.dto.City
import com.example.weather.entity.dto.CityList
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*

const val NO_DATA = "No data"

class MainViewModel(
    private val cityListUseCase: CityListUseCase,
    private val currentWeatherUseCase : CurrentWeatherUseCase,
    private val dailyWeatherUseCase : DailyWeatherUseCase,
    private val lastCityUseCase: LastCityUseCase,
    private val savedCitiesUseCase: SavedCitiesUseCase
) : ViewModel() {

    private var isLocationPermissionsGranted = false

    /**
     * Function to implement when location permission received
     */
    fun locationPermissionsGranted() {
        isLocationPermissionsGranted = true
    }

    /**
     * Checking location permission
     */
    fun getIsLocationPermissionsGranted(): Boolean {
        return isLocationPermissionsGranted
    }

    //Variables and getters
    private val currentCityList = MutableStateFlow<CityList?>(null)

    private var currentTemperature = NO_DATA

    /**
     * Getting current temperature text
     */
    fun getCurrentTemperature(): String {
        return currentTemperature
    }

    private var currentWindSpeed = NO_DATA

    /**
     * Getting current wind speed text
     */
    fun getCurrentWindSpeed(): String {
        return currentWindSpeed
    }

    private var currentWeatherCode = -1

    /**
     * Getting current weather code
     */
    fun getCurrentWeatherCode(): Int {
        return currentWeatherCode
    }

    private var lastUpdate: String = NO_DATA

    /**
     * Getting text information about last weather update
     */
    fun getLastUpdate(): String {
        return lastUpdate
    }

    private var morningTemperature = NO_DATA

    /**
     * Getting average morning temperature text
     */
    fun getMorningTemperature(): String {
        return morningTemperature
    }

    private var dayTemperature = NO_DATA

    /**
     * Getting average day temperature text
     */
    fun getDayTemperature(): String {
        return dayTemperature
    }

    private var eveningTemperature = NO_DATA

    /**
     * Getting average evening temperature text
     */
    fun getEveningTemperature(): String {
        return eveningTemperature
    }

    private var nightTemperature = NO_DATA

    /**
     * Getting average night temperature text
     */
    fun getNightTemperature(): String {
        return nightTemperature
    }

    private var chosenCity: City? = null

    /**
     * Choosing a city
     */
    fun chooseCity(city: City) {
        chosenCity = city
    }

    /**
     * Getting chosen city
     */
    fun getChosenCity(): City? {
        return chosenCity
    }

    /**
     * Clearing information about chosen city
     */
    fun clearChosenCity() {
        chosenCity = null
    }

    /**
     * Getting list of cities by string
     */
    suspend fun getCityList(string: String) {
        currentCityList.value = cityListUseCase.execute(string)
    }

    /**
     * Getting list of cities from currentCityList
     */
    fun getCities(): List<City> {
        val cities = mutableListOf<City>()
        if (currentCityList.value?.cityList.isNullOrEmpty()) {
            return cities
        } else {
            currentCityList.value?.cityList?.forEach {
                cities.add(it)
            }
        }
        return cities
    }

    /**
     * Getting last city name
     */
    fun getLastCityName(): String {
        return lastCityUseCase.getLastCityName()
    }

    /**
     * Getting saved cities list
     */
    fun getSavedCities(): List<City>? {
        return savedCitiesUseCase.getSavedCitiesList()
    }

    /**
     * Saving city to saved cities list
     */
    fun saveCity(city: City) {
        savedCitiesUseCase.saveCity(city)
    }

    /**
     * Deleting city from saved cities list
     */
    fun deleteCity(city: City) {
        savedCitiesUseCase.deleteCity(city)
    }

    /**
     * Saving information about last selected city
     */
    fun saveLastCity(city: City) {
        lastCityUseCase.saveLastCity(city)
    }

    /**
     * Getting device location
     */
    @SuppressLint("MissingPermission")
    fun getDeviceLocation(context: Context) {
        val fusedClient = LocationServices.getFusedLocationProviderClient(context)
        if (isLocationPermissionsGranted) {
            var locationResult = fusedClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var lastLocation = task.result
                    if (lastLocation == null) {
                        locationResult = fusedClient.getCurrentLocation(
                            Priority.PRIORITY_HIGH_ACCURACY,
                            CancellationTokenSource().token
                        )
                        locationResult.addOnCompleteListener {
                            if (it.isSuccessful) {
                                lastLocation = it.result
                                val deviceLocation = CityDto(
                                    name = context.getString(R.string.my_location_string),
                                    latitude = lastLocation.latitude,
                                    longitude = lastLocation.longitude,
                                    country = null
                                )
                                saveLastCity(deviceLocation)
                                CancellationTokenSource().token
                            } else {
                                Log.d("Location", "Current location result isn't successful")
                            }
                        }
                    } else {
                        val deviceLocation = CityDto(
                            name = context.getString(R.string.my_location_string),
                            latitude = lastLocation.latitude,
                            longitude = lastLocation.longitude,
                            country = null
                        )
                        saveLastCity(deviceLocation)
                    }
                } else {
                    Log.d("Location", "Last location result isn't successful")
                }
            }
        } else {
            Log.d("Location", "Permissions isn't granted")
        }
    }

    /**
     * Getting average temperature for different times of the day
     */
    private fun setDailyWeather(temperatureList: List<String>) {
        val dailyWeatherTemperatureList = dailyWeatherUseCase.execute(temperatureList)

        morningTemperature = dailyWeatherTemperatureList[0]
        dayTemperature = dailyWeatherTemperatureList[1]
        eveningTemperature = dailyWeatherTemperatureList[2]
        nightTemperature = dailyWeatherTemperatureList[3]
    }

    /**
     * Getting information about current weather
     */
    suspend fun getCurrentCityWeather() {
        val city = lastCityUseCase.getLastCity()
        if (city != null) {
            val latitude = city.latitude
            val longitude = city.longitude
            if (latitude != 0.0 && longitude != 0.0) {
                val currentWeather = currentWeatherUseCase.execute(
                    latitude = latitude,
                    longitude = longitude
                )
                if (currentWeather != null) {
                    currentTemperature = currentWeather.currentWeather.currentTemperature
                    currentWindSpeed = currentWeather.currentWeather.windSpeed
                    currentWeatherCode = currentWeather.currentWeather.weatherCode
                    val time = Calendar.getInstance().timeInMillis
                    val date = SimpleDateFormat(
                        "dd MMM HH:mm",
                        Locale.getDefault()
                    ).format(time)
                    lastUpdate = date
                    setDailyWeather(currentWeather.weatherList.temperatureList)
                } else Log.d("getCurrentCityWeather", "current weather is null")
            } else Log.d("getCurrentCityWeather", "latitude and longitude is 0.0")
        } else Log.d("getCurrentCityWeather", "Don't find any city")
    }
}