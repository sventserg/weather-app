package com.example.weather.di

import android.content.Context
import com.example.weather.data.repository.CurrentWeatherRepository
import com.example.weather.data.repository.GeocodingRepository
import com.example.weather.data.repository.LastCityRepository
import com.example.weather.data.repository.SavedCitiesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * DataModule - contains dependencies for data module
 */

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository() : CurrentWeatherRepository {
        return CurrentWeatherRepository()
    }

    @Provides
    @Singleton
    fun provideGeocodingRepository() : GeocodingRepository {
        return GeocodingRepository()
    }

    @Provides
    @Singleton
    fun provideLastCityRepository(context: Context) : LastCityRepository {
        return LastCityRepository(context)
    }

    @Provides
    @Singleton
    fun provideSavedCitiesRepository(context: Context) : SavedCitiesRepository {
        return SavedCitiesRepository(context)
    }
}