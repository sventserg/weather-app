package com.example.weather.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.example.weather.data.dto.CityDto
import com.example.weather.entity.dto.City

private const val LAST_CITY_PREFERENCES = "last_city_preferences"
private const val LAST_CITY = "last_city"
private const val LAST_CITY_LATITUDE = "last_city_latitude"
private const val LAST_CITY_LONGITUDE = "last_city_longitude"
private const val LAST_CITY_COUNTRY = "last_city_country"
const val PRESS_CITY_BUTTON = "Press choose city button"
private const val NO_NAME = "No name"
private const val ZERO_COORDINATE = "0"
private const val NO_COUNTRY = "null"

/**
 * LastCityStorage - storage for information about last selected city
 */

class LastCityStorage {
    private var lastCityPreferences: SharedPreferences? = null
    private var lastCityEditor: SharedPreferences.Editor? = null


    /**
     * getting preferences with information about last selected city
     */
    fun getLastCityPreferences(context: Context) {
        lastCityPreferences =
            context.getSharedPreferences(LAST_CITY_PREFERENCES, Context.MODE_PRIVATE)
        lastCityPreferences?.let {
            lastCityEditor = it.edit()
        }
    }

    /**
     * save city information to preferences
     */
    fun putLastCityToPreferences(city: City) {
        if (lastCityEditor == null) {
            return
        } else {
            lastCityEditor?.let {
                it.clear()
                it.putString(LAST_CITY, city.name)
                it.putString(LAST_CITY_LATITUDE, city.latitude.toString())
                it.putString(LAST_CITY_LONGITUDE, city.longitude.toString())
                it.putString(LAST_CITY_COUNTRY, city.country.toString())
                it.apply()
            }
        }
    }

    /**
     * get last city information
     */
    fun getLastCityFromPreferences(): CityDto? {
        var lastCity: CityDto? = null
        lastCityPreferences?.let {
            val name = it.getString(LAST_CITY, NO_NAME)
            val latitude = it.getString(LAST_CITY_LATITUDE, ZERO_COORDINATE)?.toDouble()
            val longitude = it.getString(LAST_CITY_LONGITUDE, ZERO_COORDINATE)?.toDouble()
            var country: String? = it.getString(LAST_CITY_COUNTRY, NO_COUNTRY)
            if (country == "null") {
                country = null
            }
            if (name != null && latitude != null && longitude != null) {
                lastCity = CityDto(
                    name = name,
                    latitude = latitude,
                    longitude = longitude,
                    country = country
                )
            }
        }
        return lastCity
    }

    /**
     * get last city name
     */
    fun getLastCityName(): String {
        var result = PRESS_CITY_BUTTON
        lastCityPreferences?.let {
            result = it.getString(LAST_CITY, PRESS_CITY_BUTTON) ?: PRESS_CITY_BUTTON
        }
        return result
    }
}