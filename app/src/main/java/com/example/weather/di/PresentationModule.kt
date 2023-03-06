package com.example.weather.di

import android.app.Application
import android.content.Context
import com.example.weather.domain.*
import com.example.weather.presentation.viewModel.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * PresentationModule - contains dependencies for presentation module
 */

@Module
class PresentationModule(
    private val application: Application
) {

    @Provides
    @Singleton
    fun provideMainViewModule(
        cityListUseCase: CityListUseCase,
        currentWeatherUseCase: CurrentWeatherUseCase,
        dailyWeatherUseCase: DailyWeatherUseCase,
        lastCityUseCase: LastCityUseCase,
        savedCitiesUseCase: SavedCitiesUseCase
    ): MainViewModel {
        return MainViewModel(
            cityListUseCase,
            currentWeatherUseCase,
            dailyWeatherUseCase,
            lastCityUseCase,
            savedCitiesUseCase
        )
    }

    @Provides
    fun provideContext() : Context {
        return application
    }
}