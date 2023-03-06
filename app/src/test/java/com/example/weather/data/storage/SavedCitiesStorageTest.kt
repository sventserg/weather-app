package com.example.weather.data.storage

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.data.dto.CityDto
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SavedCitiesStorageTest {

    lateinit var savedCitiesStorage: SavedCitiesStorage
    lateinit var context: Context
    private val city1 = CityDto("City 1", 1.0, 1.0, "Country of City 1")
    private val city2 = CityDto("City 2", 2.0, 2.0, "Country of City 2")
    private val city3 = CityDto("City 3", 3.0, 3.0, "Country of City 3")
    private val noCity = CityDto("No name", 0.0, 0.0, null)

    @Before
    fun createSavedCitiesStorage() {
        context = ApplicationProvider.getApplicationContext()
        savedCitiesStorage = SavedCitiesStorage()
        savedCitiesStorage.getSavedCitiesPreferences(context)
    }

    @Test
    fun saveOneCity() {
        savedCitiesStorage.saveCityToPreferences(city1)
        val citiesList = savedCitiesStorage.getSavedCities()
        val firstCity = citiesList?.first()

        Assert.assertEquals(citiesList?.size, 1)

        Assert.assertEquals(firstCity?.name, city1.name)
        Assert.assertEquals(firstCity?.latitude, city1.latitude)
        Assert.assertEquals(firstCity?.longitude, city1.longitude)
        Assert.assertEquals(firstCity?.country, city1.country)
    }

    @Test
    fun saveThreeCities() {
        savedCitiesStorage.saveCityToPreferences(city1)
        savedCitiesStorage.saveCityToPreferences(city2)
        savedCitiesStorage.saveCityToPreferences(city3)
        val citiesList = savedCitiesStorage.getSavedCities()
        val firstCity = citiesList?.get(0)
        val secondCity = citiesList?.get(1)
        val thirdCity = citiesList?.get(2)

        Assert.assertEquals(citiesList?.size, 3)

        Assert.assertEquals(firstCity?.name, city1.name)
        Assert.assertEquals(firstCity?.latitude, city1.latitude)
        Assert.assertEquals(firstCity?.longitude, city1.longitude)
        Assert.assertEquals(firstCity?.country, city1.country)

        Assert.assertEquals(secondCity?.name, city2.name)
        Assert.assertEquals(secondCity?.latitude, city2.latitude)
        Assert.assertEquals(secondCity?.longitude, city2.longitude)
        Assert.assertEquals(secondCity?.country, city2.country)

        Assert.assertEquals(thirdCity?.name, city3.name)
        Assert.assertEquals(thirdCity?.latitude, city3.latitude)
        Assert.assertEquals(thirdCity?.longitude, city3.longitude)
        Assert.assertEquals(thirdCity?.country, city3.country)
    }

    @Test
    fun deleteCity() {
        savedCitiesStorage.saveCityToPreferences(city1)
        savedCitiesStorage.saveCityToPreferences(city2)
        savedCitiesStorage.saveCityToPreferences(city3)

        savedCitiesStorage.deleteCityFromPreferences(city2)

        val citiesList = savedCitiesStorage.getSavedCities()
        val firstCity = citiesList?.get(0)
        val secondCity = citiesList?.get(1)

        Assert.assertEquals(citiesList?.size, 2)

        Assert.assertEquals(firstCity?.name, city1.name)
        Assert.assertEquals(firstCity?.latitude, city1.latitude)
        Assert.assertEquals(firstCity?.longitude, city1.longitude)
        Assert.assertEquals(firstCity?.country, city1.country)

        Assert.assertEquals(secondCity?.name, city3.name)
        Assert.assertEquals(secondCity?.latitude, city3.latitude)
        Assert.assertEquals(secondCity?.longitude, city3.longitude)
        Assert.assertEquals(secondCity?.country, city3.country)
    }

    @Test
    fun sortedList() {
        savedCitiesStorage.saveCityToPreferences(city3)
        savedCitiesStorage.saveCityToPreferences(city1)
        savedCitiesStorage.saveCityToPreferences(city2)

        val citiesList = savedCitiesStorage.getSavedCities()
        val firstCity = citiesList?.get(0)
        val secondCity = citiesList?.get(1)
        val thirdCity = citiesList?.get(2)

        Assert.assertEquals(citiesList?.size, 3)

        Assert.assertEquals(firstCity?.name, city1.name)
        Assert.assertEquals(firstCity?.latitude, city1.latitude)
        Assert.assertEquals(firstCity?.longitude, city1.longitude)
        Assert.assertEquals(firstCity?.country, city1.country)

        Assert.assertEquals(secondCity?.name, city2.name)
        Assert.assertEquals(secondCity?.latitude, city2.latitude)
        Assert.assertEquals(secondCity?.longitude, city2.longitude)
        Assert.assertEquals(secondCity?.country, city2.country)

        Assert.assertEquals(thirdCity?.name, city3.name)
        Assert.assertEquals(thirdCity?.latitude, city3.latitude)
        Assert.assertEquals(thirdCity?.longitude, city3.longitude)
        Assert.assertEquals(thirdCity?.country, city3.country)
    }
}