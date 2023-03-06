package com.example.weather.di

import com.example.weather.data.repository.CurrentWeatherRepository
import com.example.weather.data.repository.GeocodingRepository
import com.example.weather.data.repository.LastCityRepository
import com.example.weather.data.repository.SavedCitiesRepository
import com.example.weather.domain.*
import dagger.Module
import dagger.Provides

/**
 * DomainModule - contains dependencies for domain module
 */

@Module
class DomainModule {

    @Provides
    fun provideCityListUseCase(geocodingRepository: GeocodingRepository): CityListUseCase {
        return CityListUseCase(geocodingRepository)
    }

    @Provides
    fun provideCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository): CurrentWeatherUseCase {
        return CurrentWeatherUseCase(currentWeatherRepository)
    }

    @Provides
    fun provideDailyWeatherUseCase(): DailyWeatherUseCase {
        return DailyWeatherUseCase()
    }

    @Provides
    fun provideLastCityUseCase(lastCityRepository: LastCityRepository): LastCityUseCase {
        return LastCityUseCase(lastCityRepository)
    }

    @Provides
    fun provideSavedCitiesUseCase(savedCitiesRepository: SavedCitiesRepository): SavedCitiesUseCase {
        return SavedCitiesUseCase(savedCitiesRepository)
    }
}