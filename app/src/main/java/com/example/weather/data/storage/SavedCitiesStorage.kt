package com.example.weather.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.weather.data.dto.CityDto
import com.example.weather.entity.dto.City


private const val SAVED_CITIES_PREFERENCES = "saved_cities_preferences"
private const val SAVED_CITIES_SET = "saved_cities_set"
private const val ADD_FOR_LATITUDE = "_latitude"
private const val ADD_FOR_LONGITUDE = "_longitude"
private const val ADD_FOR_COUNTRY = "_country"
private const val ZERO_COORDINATE = "0"

/**
 * SavedCitiesStorage - storage for information about saved cities
 */

class SavedCitiesStorage {
    private var citiesSet = mutableSetOf<String>()
    private var savedCitiesPreferences: SharedPreferences? = null
    private var savedCitiesEditor: SharedPreferences.Editor? = null

    /**
     * getting preferences with saved cities list
     */
    fun getSavedCitiesPreferences(context: Context) {
        savedCitiesPreferences =
            context.getSharedPreferences(SAVED_CITIES_PREFERENCES, Context.MODE_PRIVATE)
        savedCitiesPreferences?.let {
            savedCitiesEditor = it.edit()
        }
    }

    /**
     * save city to saved cities list
     */
    fun saveCityToPreferences(city: City) {
        savedCitiesPreferences?.let {
            citiesSet = it.getStringSet(
                SAVED_CITIES_SET,
                mutableSetOf()
            ) as MutableSet<String>
        }
        citiesSet.add(city.name)
        savedCitiesEditor?.let {
            it.remove(SAVED_CITIES_SET)
            it.remove("${city.name}$ADD_FOR_LATITUDE")
            it.remove("${city.name}$ADD_FOR_LONGITUDE")
            it.remove("${city.name}$ADD_FOR_COUNTRY")
            it.putStringSet(SAVED_CITIES_SET, citiesSet)
            it.putString("${city.name}$ADD_FOR_LATITUDE", city.latitude.toString())
            it.putString("${city.name}$ADD_FOR_LONGITUDE", city.longitude.toString())
            if (!city.country.isNullOrEmpty()) {
                it.putString("${city.name}$ADD_FOR_COUNTRY", city.country)
            }
            it.apply()
        }
    }

    /**
     * delete city from saved cities list
     */
    fun deleteCityFromPreferences(city: City) {
        savedCitiesPreferences?.let {
            citiesSet = it.getStringSet(
                SAVED_CITIES_SET,
                mutableSetOf()
            ) as MutableSet<String>
        }
        citiesSet.remove(city.name)
        savedCitiesEditor?.let {
            it.remove("${city.name}$ADD_FOR_LATITUDE")
            it.remove("${city.name}$ADD_FOR_LONGITUDE")
            it.remove("${city.name}$ADD_FOR_COUNTRY")
            it.putStringSet(SAVED_CITIES_SET, citiesSet)
            it.apply()
        }
    }

    /**
     * getting saved cities list
     */
    fun getSavedCities(): List<City>? {
        savedCitiesPreferences?.let {
            citiesSet = it.getStringSet(
                SAVED_CITIES_SET,
                mutableSetOf()
            ) as MutableSet<String>
        }
        val cityNames = mutableListOf<String>()
        citiesSet.forEach {
            cityNames.add(it)
        }
        val sortedCityNames = cityNames.sorted()
        val cityList = mutableListOf<City>()
        savedCitiesPreferences?.let { pref ->
            sortedCityNames.forEach {
                val latitude = pref.getString("${it}$ADD_FOR_LATITUDE", ZERO_COORDINATE)?.toDouble()
                val longitude = pref.getString("${it}$ADD_FOR_LONGITUDE", ZERO_COORDINATE)?.toDouble()
                val country: String? = pref.getString("${it}$ADD_FOR_COUNTRY", null)
                if (latitude != null && longitude != null) {
                    cityList.add(
                        CityDto(
                            name = it,
                            latitude = latitude,
                            longitude = longitude,
                            country = country
                        )
                    )
                }
            }
        }
        return if (cityList.isEmpty()) null
        else cityList
    }
}