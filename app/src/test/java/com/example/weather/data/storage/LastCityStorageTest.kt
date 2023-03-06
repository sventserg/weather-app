package com.example.weather.data.storage

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.data.dto.CityDto
import com.example.weather.entity.dto.City
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LastCityStorageTest {

    lateinit var lastCityStorage: LastCityStorage
    lateinit var context: Context
    var lastCity: City? = null
    private val city1 = CityDto("City 1", 1.0, 1.0, "Country of City 1")
    private val city2 = CityDto("City 2", 2.0, 2.0, "Country of City 2")
    private val city3 = CityDto("City 3", 3.0, 3.0, "Country of City 3")
    private val noCity = CityDto("No name", 0.0, 0.0, null)


    @Before
    fun createLastCityStorage() {
        context = ApplicationProvider.getApplicationContext()
        lastCityStorage = LastCityStorage()
        lastCityStorage.getLastCityPreferences(context)
    }

    @Test
    fun putOneCity() {
        lastCityStorage.putLastCityToPreferences(city1)
        lastCity = lastCityStorage.getLastCityFromPreferences()
        Assert.assertEquals(lastCity?.name, city1.name)
        Assert.assertEquals(lastCity?.latitude, city1.latitude)
        Assert.assertEquals(lastCity?.longitude, city1.longitude)
        Assert.assertEquals(lastCity?.country, city1.country)
    }

    @Test
    fun putThreeCities() {
        lastCityStorage.putLastCityToPreferences(city1)
        lastCityStorage.putLastCityToPreferences(city2)
        lastCityStorage.putLastCityToPreferences(city3)
        lastCity = lastCityStorage.getLastCityFromPreferences()
        Assert.assertEquals(lastCity?.name, city3.name)
        Assert.assertEquals(lastCity?.latitude, city3.latitude)
        Assert.assertEquals(lastCity?.longitude, city3.longitude)
        Assert.assertEquals(lastCity?.country, city3.country)
    }

    @Test
    fun putNoCity() {
        lastCity = lastCityStorage.getLastCityFromPreferences()
        Assert.assertEquals(lastCity?.name, noCity.name)
        Assert.assertEquals(lastCity?.latitude, noCity.latitude)
        Assert.assertEquals(lastCity?.longitude, noCity.longitude)
        Assert.assertEquals(lastCity?.country, noCity.country)
    }

}