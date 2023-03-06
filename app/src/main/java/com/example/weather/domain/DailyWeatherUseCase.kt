package com.example.weather.domain

import com.example.weather.entity.domain.DailyWeatherUseCase
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * DailyWeatherUseCase - get hourly weather forecast calculate and return list of
 * average morning temperature, average day temperature,
 * average evening temperature and average night temperature
 */

class DailyWeatherUseCase : DailyWeatherUseCase {
    override fun execute(temperatureList: List<String>): List<String> {
        var morning = 0.0
        var day = 0.0
        var evening = 0.0
        var night = 0.0
        val dailyTemperatureList = mutableListOf<Double>()
        var counter = 0
        while (counter < 24) {
            dailyTemperatureList.add(temperatureList[counter].toDouble())
            counter++
        }
        counter = 0
        while (counter < 6) {
            morning += dailyTemperatureList[counter]
            counter++
        }
        morning /= 6

        while (counter < 12) {
            day += dailyTemperatureList[counter]
            counter++
        }
        day /= 6

        while (counter < 18) {
            evening += dailyTemperatureList[counter]
            counter++
        }
        evening /= 6

        while (counter < 24) {
            night += dailyTemperatureList[counter]
            counter++
        }
        night /= 6

        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.DOWN

        val morningTemperature = df.format(morning).toString()
        val dayTemperature = df.format(day).toString()
        val eveningTemperature = df.format(evening).toString()
        val nightTemperature = df.format(night).toString()

        return listOf(morningTemperature, dayTemperature, eveningTemperature, nightTemperature)
    }
}