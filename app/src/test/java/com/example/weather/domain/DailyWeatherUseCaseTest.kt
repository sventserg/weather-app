package com.example.weather.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.RoundingMode
import java.text.DecimalFormat

class DailyWeatherUseCaseTest {

    private val temperatureList = mutableListOf<String>()
    private var counter = 0
    private val dailyWeatherUseCase = DailyWeatherUseCase()
    private var morningTemperature: String? = null
    private var dayTemperature: String? = null
    private var eveningTemperature: String? = null
    private var nightTemperature: String? = null

    @Before
    fun temperatureListInit() {
        while (counter < 50) {
            temperatureList.add(counter.toString())
            counter++
        }

        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.DOWN

        val morning = (0.0 + 1 + 2 + 3 + 4 + 5) / 6
        val day = (6.0 + 7 + 8 + 9 + 10 + 11) / 6
        val evening = (12.0 + 13 + 14 + 15 + 16 + 17) / 6
        val night = (18.0 + 19 + 20 + 21 + 22 + 23) / 6

        morningTemperature = df.format(morning).toString()
        dayTemperature = df.format(day).toString()
        eveningTemperature = df.format(evening).toString()
        nightTemperature = df.format(night).toString()
    }

    @Test
    fun dailyWeatherCalculating() {
        val dailyWeather = dailyWeatherUseCase.execute(temperatureList)
        Assert.assertEquals(dailyWeather.size, 4)
    }

    @Test
    fun morningTemperatureCalculating() {
        val dailyWeather = dailyWeatherUseCase.execute(temperatureList)
        val morningCalculatedTemperature = dailyWeather[0]

        Assert.assertEquals(morningTemperature, morningCalculatedTemperature)
    }

    @Test
    fun dayTemperatureCalculating() {
        val dailyWeather = dailyWeatherUseCase.execute(temperatureList)
        val dayCalculatedTemperature = dailyWeather[1]
        Assert.assertEquals(dayTemperature, dayCalculatedTemperature)
    }

    @Test
    fun eveningTemperatureCalculating() {
        val dailyWeather = dailyWeatherUseCase.execute(temperatureList)
        val eveningCalculatedTemperature = dailyWeather[2]
        Assert.assertEquals(eveningTemperature, eveningCalculatedTemperature)
    }

    @Test
    fun nightTemperatureCalculating() {
        val dailyWeather = dailyWeatherUseCase.execute(temperatureList)
        val nightCalculatedTemperature = dailyWeather[3]
        Assert.assertEquals(nightTemperature, nightCalculatedTemperature)
    }

}